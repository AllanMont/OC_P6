import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Jwt, User } from '../interfaces/user.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiURL = environment.API_URL + '/api/auth';

  constructor(private http: HttpClient) { }

  login(credentials: { emailOrName: string, password: string }): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.apiURL}/login`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
            return response;
          }
          throw new Error('Invalid response from server');
        }),
        catchError((error: any) => {
          return throwError('Failed to log in. Please try again.');
        })
      );
  }

  register(credentials: { name: string, email: string, password: string }): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.apiURL}/register`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
            return response;
          }
          throw new Error('Invalid response from server');
        }),
        catchError((error: any) => {
          return throwError('Failed to register. Please try again.', error);
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
    return this.http.get<User>(`${this.apiURL}/me`)
      .pipe(
        catchError((error: any) => {
          return throwError('Failed to fetch user information.');
        })
      );
  }

  logout() {
    sessionStorage.removeItem('jwt');
  }
}
