import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { CommentService } from 'src/app/core/services/comment.service';
import { UserService } from 'src/app/core/services/user.service';
import { TopicService } from 'src/app/core/services/topic.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  postId: number = 0;
  post: any;
  newComment: string = '';
  comments: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private commentService: CommentService,
    private userService: UserService,
    private topicService: TopicService
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.postId = +params['id'];
      this.getPostDetails();
      this.getComments();
    });
  }

  onComment() {
    if (this.newComment.trim()) {
      const commentToAdd = {
        content: this.newComment,
        postId: this.post.id,
        username: ''
      };

      this.commentService.createComment(commentToAdd).subscribe(
        (response: any) => {
          this.userService.getUserLogged().subscribe(
            ( user: { name: any; }) => {
              commentToAdd.username = user.name;
              this.comments.push(commentToAdd);
              this.newComment = '';
            },
            (error: any) => {
              console.error('Erreur lors de la récupération du nom de l\'utilisateur :', error);
            }
          );
        },
        error => {
          console.error('Erreur lors de l\'ajout du commentaire :', error);
        }
      );
    }
  }
  
  getPostDetails() {
    this.postService.getPostById(this.postId).subscribe(
      (response: any) => {
        this.post = response;
        this.topicService.getTopicById(this.post.topicId).subscribe(
          (topic: any) => {
            this.post.topicName = topic.name;
          },
          error => {
            console.error('Erreur lors de la récupération des détails du sujet :', error);
          }
        );
      },
      error => {
        console.error('Erreur lors de la récupération des détails de l\'article :', error);
      }
    );
  }

  getComments() {
    this.commentService.getAllCommentsByPostId(this.postId).subscribe(
      (response: any) => {
        this.comments = response;
        this.comments.forEach(comment => {
          this.userService.getUserById(comment.authorId).subscribe(
            (user: { name: any; }) => {
              comment.username = user.name;
            },
            (error: any) => {
              console.error('Erreur lors de la récupération du nom de l\'utilisateur :', error);
            }
          );
        });
      },
      error => {
        console.error('Erreur lors de la récupération des commentaires :', error);
      }
    );
  }
}
