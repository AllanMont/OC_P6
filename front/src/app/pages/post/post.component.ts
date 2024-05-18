import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/core/services/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  posts: any[] = [];
  sortedPosts: any[] = [];
  sortBy: string = 'date';

  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.getPosts();
  }

  getPosts(): void {
    this.postService.getAllPosts().subscribe(
      (data: any) => {
        this.posts = data;
        this.sortPosts();
      },
      error => {
        console.error('Erreur lors de la récupération des articles :', error);
      }
    );
  }

  sortPosts(): void {
    this.sortedPosts = [...this.posts].sort((a, b) => {
      if (this.sortBy === 'date') {
        return new Date(b.date).getTime() - new Date(a.date).getTime();
      } else if (this.sortBy === 'author') {
        return a.author.localeCompare(b.author);
      }
      else if (this.sortBy === 'title') {
        return a.title.localeCompare(b.title);
      }
      return 0;
    });
  }

  onSortChange(): void {
    this.sortPosts();
  }
}
