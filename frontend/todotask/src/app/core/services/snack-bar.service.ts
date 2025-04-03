import { inject, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnackBarService {
  snackBar = inject(MatSnackBar);
  
  constructor() { }

  showMessage(message: string, action: string = 'Cerrar', duration : number = 3000):void{
    this.snackBar.open(message,action,{
      duration,
      verticalPosition: 'bottom',
      horizontalPosition: 'center'
    })
  }
}
