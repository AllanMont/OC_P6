import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TopicService {
  apiURL = environment.API_URL + '/topics';

  constructor(private http: HttpClient) { }

  getAllTopics() {
    return this.http.get(`${this.apiURL}`);
  }

  getTopicById(id: number) {
    return this.http.get(`${this.apiURL}/${id}`);
  }

  createTopic(topic: any) {
    return this.http.post(`${this.apiURL}`, topic);
  }
}
