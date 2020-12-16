import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'dynamic-form-generation';
  isAuthenticated = false;

  constructor(private authService: AuthService, private router: Router) {
    this.authService
      .checkAuthenticated()
      .subscribe((isAuthenticated: boolean) => {
        this.isAuthenticated = isAuthenticated;
      });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
