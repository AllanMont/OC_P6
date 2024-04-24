import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  onSubmit() {
    if (this.loginForm.valid) {
      const user = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };

      this.authService.login(user).subscribe(
        response => {
          // Connexion réussie, rediriger l'utilisateur vers une autre page par exemple
          console.log('Connexion réussie :', response);
        },
        error => {
          console.error('Erreur lors de la connexion :', error);
          this.errorMessage = 'Adresse e-mail ou mot de passe incorrect.';
        }
      );
    } else {
      console.log('Formulaire invalide, veuillez vérifier les champs.');
    }
  }
}
