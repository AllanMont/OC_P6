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
        this.comments = response;
      },
      error => {
        console.error('Erreur lors de la récupération des commentaires :', error);
      }
    );
  }
}
