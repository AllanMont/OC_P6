import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  apiURL = environment.API_URL + '/comments';

  constructor(private http: HttpClient) { }

  getAllCommentsByPostId(postId: number) {
    return this.http.get(`${this.apiURL}/post/${postId}`, this.getHttpOptions());
  }

  createComment(comment: any) {
    return this.http.post(`${this.apiURL}`, comment, this.getHttpOptions());
  }

  deleteComment(id: number) {
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
