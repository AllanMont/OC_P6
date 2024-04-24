import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
apiURL = environment.API_URL + '/posts';

  constructor(private http: HttpClient) { }

  getAllPosts() {
    return this.http.get(`${this.apiURL}/all`);
  }

  getPostById(id: number) {
    return this.http.get(`${this.apiURL}/${id}`);
  }

  createPost(post: any) {
    return this.http.post(`${this.apiURL}`, post);
  }

  deletePost(id: number) {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}
