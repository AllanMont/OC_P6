import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor() { }

  ngOnInit(): void {

  }

  get formControls() {
    return this.registerForm.controls;
  }

  onSubmit() {
  if (this.registerForm.valid) {
    console.log('Formulaire valide, soumission en cours...');
  } else {
    console.log('Formulaire invalide, veuillez vérifier les champs.');
  }
}


}
