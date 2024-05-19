import { Component, OnInit } from '@angular/core';
import { TopicService } from 'src/app/core/services/topic.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.scss']
})
export class TopicComponent implements OnInit {
  topics: any[] = [];

  constructor(
    private topicService: TopicService,
    private subscriptionService: SubscriptionService
  ) { }

  ngOnInit(): void {
    this.loadAllTopics();
  }

  onSubscribeOrUnSubscribe(topic: any) {
    if(topic.subscribed) {
      this.onUnsubscribe(topic);
    } else {
      this.onSubscribe(topic);
    }
  }

  onSubscribe(topic: any) {
    this.topicService.subscribeToTopic(topic.id).subscribe(
      (data: any) => {
        topic.subscribed = true;
      },
      error => {
        if(error.status === 409) {
          alert('Vous êtes déjà abonné à ce thème');
          return;
        }
        console.error('Erreur lors de l\'abonnement au thème :', error);
      }
    );
  }

  onUnsubscribe(topic: any) {
    this.subscriptionService.deleteSubscription(topic.id).subscribe(
      (data: any) => {
        topic.subscribed = false;
      },
      error => {
        console.error('Erreur lors de la désinscription du thème :', error);
      }
    );
  }

  loadAllTopics() {
    this.topicService.getAllTopics().subscribe(
      (data: any) => {
        this.topics = data;
        this.topics.forEach(topic => {
          this.subscriptionService.isSubscriptionExist(topic.id).subscribe(
            (isSubscribed: Object) => {
              topic.subscribed = isSubscribed;
            },
            error => {
              console.error('Erreur lors de la vérification de l\'abonnement au thème :', error);
            }
          );
        });
      },
      error => {
        console.error('Erreur lors du chargement des thèmes :', error);
      }
    );
  }

}
