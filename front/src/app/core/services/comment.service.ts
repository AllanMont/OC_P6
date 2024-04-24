import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  apiURL = environment.API_URL + '/comments';

  constructor(private http: HttpClient) { }

  getAllCommentsByPostId(postId: number) {
    return this.http.get(`${this.apiURL}/post/${postId}`);
  }

  createComment(comment: any) {
    return this.http.post(`${this.apiURL}`, comment);
  }

  deleteComment(id: number) {
    return this.http.delete(`${this.apiURL}/${id}`);
  }
}
