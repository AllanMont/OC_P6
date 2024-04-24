import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
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
