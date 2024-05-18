import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class UnauthGuard implements CanActivate {

  constructor(private authService: AuthenticationService, private router: Router) {}

  canActivate(): boolean {
    if (!this.authService.getToken()) {
      // User is not logged in, allow access
      return true;
    }
    // User is logged in, redirect to home page
    this.router.navigate(['/post']);
    return false;
  }
}
