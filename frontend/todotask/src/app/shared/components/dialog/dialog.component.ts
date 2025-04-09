import { Component, Inject } from '@angular/core';
import { MatButton } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  imports: [MatDialogModule,MatButton],
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.css'
})
export class DialogComponent {

  constructor( 
    @Inject(MAT_DIALOG_DATA) public data: { title: string; message: string,imagePath?:string},
    private dialogRef: MatDialogRef<DialogComponent>)
    {}

    close(): void {
      this.dialogRef.close();
    }
}
