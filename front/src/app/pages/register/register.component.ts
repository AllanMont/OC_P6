import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder,private router: Router) {
    this.registerForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const user: { name: string, email: string, password: string } = {
        name: this.registerForm.value.name,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };

      this.authService.register(user).subscribe(
        response => {
          console.log('Inscription réussie :', response);
          this.router.navigate(['/profile']); 
        },
        error => {
          console.error('Erreur lors de l\'inscription :', error);
          this.errorMessage = 'Une erreur s\'est produite lors de l\'inscription. Veuillez réessayer.';
        }
      );
    } else {
      console.log('Formulaire invalide, veuillez vérifier les champs.');
    }
  }
}
