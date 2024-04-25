import { Component, OnInit } from '@angular/core';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit {
  topics: any[] = [];

  constructor(private topicService: TopicService) { }

  ngOnInit(): void {
    this.loadAllTopics();
  }

  loadAllTopics() {
    this.topicService.getAllTopics().subscribe(
      (data: any) => {
        this.topics = data;
      },
      error => {
        console.error('Erreur lors du chargement des thèmes :', error);
      }
    );
  }
}
