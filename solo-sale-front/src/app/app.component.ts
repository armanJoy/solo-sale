import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './shared/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isAuthenticated = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isAuthenticated = this.authService.isAuthenticated();

    if (!this.isAuthenticated) {
      this.router.navigate(['/login']);
    } 
  }

  // ngOnInit(): void {
  //   const token = localStorage.getItem('accesstoken');

  //   if (token) {
  //     this.isAuthenticated = true;  
  //     this.router.navigate(['/']); 
  //   } else {
  //     this.isAuthenticated = false; 
  //     this.router.navigate(['/login']);
  //   }
  // }
}
