import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from 'src/app/core/services/post.service';
import { TopicService } from 'src/app/core/services/topic.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss']
})
export class PostCreateComponent implements OnInit {

  postForm: FormGroup | any;
  message: string = '';
  topics: any[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private postService: PostService,
    private topicService: TopicService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      selectedTopic: [null, Validators.required],
      titlePost: ['', Validators.required],
      contentPost: ['', Validators.required]
    });

    this.loadTopics();
  }

  loadTopics(): void {
    this.topicService.getAllTopics().subscribe((data: any) => {
      this.topics = data;
    }, error => {
      console.error('Erreur lors du chargement des topics', error);
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      const post = {
        title: this.postForm.value.titlePost,
        content: this.postForm.value.contentPost,
        topicId: this.postForm.value.selectedTopic
      };
      this.postService.createPost(post).subscribe(
        response => {
          this.router.navigate(['/post']);
        },
        error => {
          console.error('Erreur lors de la création de l\'article :', error);
          this.message = 'Une erreur s\'est produite lors de la création de l\'article. Veuillez réessayer.';
        }
      );
    } else {
      this.message = 'Formulaire invalide, veuillez vérifier les champs.';
    }
  }
}
