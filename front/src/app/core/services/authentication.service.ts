import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Jwt, User } from '../interfaces/user.interface';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  apiURL = environment.API_URL + '/api/auth';

  constructor(private http: HttpClient, private router: Router) { }

  login(credentials: { email: string, password: string }): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.apiURL}/login`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.token) {
            this.setToken(response.token);
          }
          return response;
        }),
        catchError((error: any) => {
          console.error('Login error', error);
          return throwError('Failed to log in. Please try again.');
        })
      );
  }

  register(credentials: { name: string, email: string, password: string }): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.apiURL}/register`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.token) {
            this.setToken(response.token);
          }
          return response;
        }),
        catchError((error: any) => {
          console.error('Register error', error);
          return throwError(error);
        })
      );
  }

  getToken(): string | null {
    return sessionStorage.getItem('jwt');
  }

  setToken(token: string): void {
    sessionStorage.setItem('jwt', token);
  }

  infoUser(): Observable<User> {
    const token = this.getToken();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
    return this.http.get<User>(`${this.apiURL}/me`, httpOptions)
      .pipe(
        catchError((error: any) => {
          console.error('Info user error', error);
          return throwError('Failed to fetch user information.');
        })
      );
  }

  logout() {
    sessionStorage.removeItem('jwt');
    this.router.navigate(['/profile']); 
  }
}
