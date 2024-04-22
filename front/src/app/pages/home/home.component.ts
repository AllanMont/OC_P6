import { Component, OnInit } from '@angular/core';

import "../../app.component.scss"

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  login() {
    window.location.href = "/login";
  }

  register() {
    window.location.href = "/register";
  }
}
