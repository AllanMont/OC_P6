import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PostService } from 'src/app/core/services/post.service';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss']
})
export class PostCreateComponent implements OnInit {

  postForm: FormGroup | any;
  message: string = '';

  constructor(private formBuilder: FormBuilder, private postService: PostService) { }

  ngOnInit(): void {
    this.postForm = this.formBuilder.group({
      subject: ['', Validators.required],
      titlePost: ['', Validators.required],
      contentPost: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.postForm.valid) {
      const post = {
        subject: this.postForm.value.subject,
        title: this.postForm.value.titlePost,
        content: this.postForm.value.contentPost
      };
      this.postService.createPost(post).subscribe(
        response => {
          this.message = 'L\'article a été créé avec succès.';
        },
        error => {
          console.error('Erreur lors de la création de l\'article :', error);
          this.message = 'Une erreur s\'est produite lors de la création de l\'article. Veuillez réessayer.';
        }
      );
    } else {
      this.message = 'Formulaire invalide, veuillez vérifier les champs.';
      console.log('Formulaire invalide, veuillez vérifier les champs.');
    }
  }
}
