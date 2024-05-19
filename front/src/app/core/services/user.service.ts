import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
    apiURL = environment.API_URL;

    private getHttpOptions() {
        const authToken = sessionStorage.getItem('jwt');
        const httpOptions = {
          headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${authToken}`
          })
        };
        return httpOptions;
      }

  constructor(private http: HttpClient) { }

  updateUser(user: any): Observable<any> {
    return this.http.put<any>(`${this.apiURL}/users`, user, this.getHttpOptions());
  }
  
  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiURL}/users/${id}`, this.getHttpOptions());
  }

  getUserLogged(): Observable<any> {
    return this.http.get<any>(`${this.apiURL}/api/auth/me`, this.getHttpOptions());
  }
}
