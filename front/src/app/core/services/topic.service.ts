import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  apiURL = environment.API_URL + '/topics';

  constructor(private http: HttpClient) { }

  getAllTopics() {
    return this.http.get(`${this.apiURL}`, this.getHttpOptions());
  }

  getTopicById(id: number) {
    return this.http.get(`${this.apiURL}/${id}`, this.getHttpOptions());
  }

  createTopic(topic: any) {
    return this.http.post(`${this.apiURL}`, topic, this.getHttpOptions());
  }

  subscribeToTopic(topicId: number) {
    return this.http.post(`${environment.API_URL}/subscriptions`, topicId , this.getHttpOptions());
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
