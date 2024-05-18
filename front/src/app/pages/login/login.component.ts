import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {

  }

  onSubmit() {
    if (this.loginForm.valid) {
      const user: {email: string, password: string} = {
        email: this.loginForm.value.email,
        password: this.loginForm.value.password
      };

      this.authService.login(user).subscribe(
        response => {
          this.router.navigate(['/profile']); 
        },
        error => {
          console.error('Erreur lors de la connexion :', error);
          this.errorMessage = 'Adresse e-mail ou mot de passe incorrect.';
        }
      );
    } else {
      this.errorMessage = 'Formulaire invalide, veuillez vérifier les champs.';
      console.log('Formulaire invalide, veuillez vérifier les champs.');
    }
  }
}
