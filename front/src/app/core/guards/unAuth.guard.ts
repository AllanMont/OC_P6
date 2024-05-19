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
      return true;
    }
    this.router.navigate(['/post']);
    return false;
  }
}
