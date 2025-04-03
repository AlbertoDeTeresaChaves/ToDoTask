import { inject, Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { DialogComponent } from '../components/dialog/dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  private dialog = inject(MatDialog)


  openDialog(title: string , message: string,imagePath:string,newWidth: string,newHeight: string){
    this.dialog.open(DialogComponent,{
      data:{title,message,imagePath},
      width: newWidth,
      height: newHeight,
      panelClass: 'custom-dialog'
    });
  }
}
