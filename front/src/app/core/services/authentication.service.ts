import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Jwt, User } from '../interfaces/user.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  apiURL = environment.API_URL + '/api/auth';

  constructor(private http: HttpClient) { }

  login(credentials: { emailOrUsername: string, password: string }): Observable<Jwt> {
    return this.http.post(`${this.apiURL}/login`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response
        })
      )
  }

  register(credentials: { username: string, email: string, password: string }): Observable<Jwt> {
    return this.http.post(`${this.apiURL}/register`, credentials)
      .pipe(
        map((response: Jwt) => {
          if (response && response.jwt) {
            this.setToken(response.jwt);
          }
          return response
        })
      )
  }

  getToken(): string | null {
    return sessionStorage.getItem('jwt');
  }

  setToken(token: string): void {
    sessionStorage.setItem('jwt', token);
  }
  
  infoUser() {
    return this.http.get(`${this.apiURL}/me`);
  }

  logout() {
    localStorage.removeItem('token');
  }
}
