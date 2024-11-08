import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  constructor(private snackBar: MatSnackBar) {}

  showToast(type: 'success' | 'error' | 'info' | 'warning', message: string) {
    let panelClass = '';
    
    switch (type) {
      case 'success':
        panelClass = 'toast-success';
        break;
      case 'error':
        panelClass = 'toast-error';
        break;
      case 'info':
        panelClass = 'toast-info';
        break;
      case 'warning':
        panelClass = 'toast-warning';
        break;
    }

    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'bottom',
      panelClass: [panelClass],
    });
  }
}
