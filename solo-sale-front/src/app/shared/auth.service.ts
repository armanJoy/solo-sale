import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated(): boolean {
    return !!localStorage.getItem('accesstoken');
  }

  logout(): void {
    localStorage.removeItem('accesstoken');
  }
}
