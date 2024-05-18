import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthenticationService } from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  apiURL = environment.API_URL + '/subscriptions';

  constructor(private http: HttpClient, private authService: AuthenticationService) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.authService.getToken()}`
    })
  };

  isSubscriptionExist(userId: number, topicId: number) {
    return this.http.get(`${this.apiURL}/user/${userId}/topic/${topicId}`, this.httpOptions);
  }

  createSubscription(subscription: any) {
    return this.http.post(`${this.apiURL}`, subscription, this.httpOptions);
  }

  deleteSubscription(id: number) {
    return this.http.delete(`${this.apiURL}?idSubscription=${id}`, this.httpOptions);
  }

  getSubscriptionsByUserId() {
    return this.http.get(`${this.apiURL}/user`, this.httpOptions);
  }

  getTopicById(topicId: number) {
    return this.http.get(`${this.apiURL}/topic?topicId=${topicId}` , this.httpOptions);
  }
}
