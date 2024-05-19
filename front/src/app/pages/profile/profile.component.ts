import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  userId: number = 0;
  userProfile: any;
  userSubscriptions: any[] = [];
  updatedProfile = {
    name: '',
    email: '',
  };

  constructor(
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private subscriptionService: SubscriptionService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userId = params['id'];
      this.loadUserProfile(this.userId);
      this.loadUserSubscriptions();
    });
  }

  loadUserProfile(userId: number) {
    this.authenticationService.infoUser().subscribe(
      (data: any) => {
        this.userProfile = data;
      },
      error => {
        console.error('Erreur lors du chargement du profil :', error);
      }
    );
  }

  updateProfile() {
    this.userService.updateUser(this.updatedProfile).subscribe(
      (data: any) => {
        this.userProfile = data;
      },
      error => {
        console.error('Erreur lors de la mise à jour du profil :', error);
      }
    );
  }

  loadUserSubscriptions() {
    this.subscriptionService.getSubscriptionsByUserId().subscribe(
      (data: any) => {
        this.userSubscriptions = data;
        this.userSubscriptions.forEach(subscription => {
          this.subscriptionService.getTopicById(subscription.id.topicId).subscribe(
            (topicData: any) => {
              subscription.title = topicData.name;
            },
            error => {
              console.error('Erreur lors de la récupération du sujet :', error);
            }
          );
        });
      },
      error => {
        console.error('Erreur lors du chargement des abonnements :', error);
      }
    );
  }

  onUnsubscribe(subscriptionId: number) {
    this.subscriptionService.deleteSubscription(subscriptionId).subscribe(
      (data: any) => {
        this.loadUserSubscriptions();
      },
      error => {
        console.error('Erreur lors de la suppression de l\'abonnement :', error);
      }
    );
  }

  onDisconnect() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
