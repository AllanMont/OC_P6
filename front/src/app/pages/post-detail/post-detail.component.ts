import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/core/services/post.service';
import { CommentService } from 'src/app/core/services/comment.service';

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
    private commentService: CommentService
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
      const comment = {
        content: this.newComment,
        postId: this.post.id,
        username: 'Current User' // Remplacez par le nom d'utilisateur actuel
      };

      this.commentService.createComment(comment).subscribe(
        (response: any) => {
          this.comments.push(response);
          this.newComment = '';
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
      },
      error => {
        console.error('Erreur lors de la récupération des détails de l\'article :', error);
      }
    );
  }

  getComments() {
    this.commentService.getAllCommentsByPostId(this.postId).subscribe(
      (response: any) => {
        console.log('coms',response);
        this.comments = response;
      },
      error => {
        console.error('Erreur lors de la récupération des commentaires :', error);
      }
    );
  }
}
