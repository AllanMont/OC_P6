import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
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

  onDisconnect(){
    console.log('Déconnexion en cours...');
  }
}
