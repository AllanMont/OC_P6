import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  apiURL = environment.API_URL + '/subscriptions';

  constructor(private http: HttpClient) { }

  isSubscriptionExist(userId: number, topicId: number) {
    return this.http.get(`${this.apiURL}/user/${userId}/topic/${topicId}`);
  }

  createSubscription(subscription: any) {
    return this.http.post(`${this.apiURL}`, subscription);
  }

  deleteSubscription(id: number) {
    return this.http.delete(`${this.apiURL}/${id}`);
  }

  getSubscriptionsByUserId() {
    return this.http.get(`${this.apiURL}/user`);
  }
}
