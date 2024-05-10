import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  apiURL = environment.API_URL + '/posts';

  constructor(private http: HttpClient) { }

  getAllPosts() {
    return this.http.get(`${this.apiURL}/all`, this.getHttpOptions());
  }

  getPostById(id: number) {
    return this.http.get(`${this.apiURL}/${id}`, this.getHttpOptions());
  }

  createPost(post: any) {
    return this.http.post(`${this.apiURL}`, post, this.getHttpOptions());
  }

  deletePost(id: number) {
    return this.http.delete(`${this.apiURL}/${id}`, this.getHttpOptions());
  }

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
}
