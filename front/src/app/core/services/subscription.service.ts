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

  isSubscriptionExist(topicId: number) {
    return this.http.get(`${this.apiURL}?topicId=${topicId}`, this.getHttpOptions());
  }

  createSubscription(subscription: any) {
    return this.http.post(`${this.apiURL}`, subscription, this.getHttpOptions());
  }

  deleteSubscription(id: number) {
    return this.http.delete(`${this.apiURL}?idSubscription=${id}`, this.getHttpOptions());
  }

  getSubscriptionsByUserId() {
    return this.http.get(`${this.apiURL}/user`, this.getHttpOptions());
  }

  getTopicById(topicId: number) {
    return this.http.get(`${this.apiURL}/topic?topicId=${topicId}` , this.getHttpOptions());
  }
}
