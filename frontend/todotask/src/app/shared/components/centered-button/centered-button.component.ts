import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';

@Component({
  selector: 'app-centered-button',
  imports: [MatButtonModule],
  templateUrl: './centered-button.component.html',
  styleUrl: './centered-button.component.css'
})
export class CenteredButtonComponent {

  @Input() text:string = "";
  @Input() route: string | any[] = '/';

  constructor(private router: Router) {}

  goToRoute() {
    this.router.navigate(Array.isArray(this.route) ? this.route : [this.route]);
  }
}
