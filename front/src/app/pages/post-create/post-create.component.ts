import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-post-create',
  templateUrl: './post-create.component.html',
  styleUrls: ['./post-create.component.scss']
})
export class PostCreateComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({
    subject: new FormControl('', Validators.required),
    titlePost: new FormControl('', Validators.required),
    contentPost: new FormControl('', Validators.required)
  });
  constructor() { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.registerForm.valid) {
      console.log('Formulaire valide, soumission en cours...');
    } else {
      console.log('Formulaire invalide, veuillez vérifier les champs.');
    }
  }
}
