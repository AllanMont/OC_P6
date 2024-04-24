import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; // Importez Router
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {

  }

  get formControls() {
    return this.registerForm.controls;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const user = {
        username: this.registerForm.value.username,
        email: this.registerForm.value.email,
        password: this.registerForm.value.password
      };
      
      this.authService.register(user).subscribe(
        response => {
          console.log('Inscription réussie :', response);
          this.router.navigate(['/login']);
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
