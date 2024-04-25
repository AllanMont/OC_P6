import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  apiURL = environment.API_URL + '/api/auth';

  constructor(private http: HttpClient) { }

  login(user: any) {
    return this.http.post(`${this.apiURL}/login`, user);
  }

  register(user: any) {
    return this.http.post(`${this.apiURL}/register`, user);
  }

  infoUser() {
    return this.http.get(`${this.apiURL}/me`);
  }

  logout() {
    localStorage.removeItem('token');
  }
}
