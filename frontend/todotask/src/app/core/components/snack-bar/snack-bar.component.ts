import { Component, inject, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatSnackBarLabel} from '@angular/material/snack-bar';
@Component({
  selector: 'app-snack-bar',
  imports: [MatButtonModule,MatSnackBarLabel],
  templateUrl: './snack-bar.component.html',
  styleUrl: './snack-bar.component.css'
})
export class SnackBarComponent {

  @Input() message : String = '';

}
