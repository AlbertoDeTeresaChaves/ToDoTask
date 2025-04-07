import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatSidenav, MatSidenavContainer, MatSidenavModule} from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {MatListModule} from '@angular/material/list';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { User } from '../../models/user';
@Component({
  selector: 'app-main-screen',
  imports: [MatToolbarModule,MatIconModule,MatButtonModule,RouterOutlet,MatSidenavModule,MatSidenav,MatSidenavContainer,MatListModule,MatMenuModule,RouterLink],
  templateUrl: './main-screen.component.html',
  styleUrl: './main-screen.component.css'
})
export class MainScreenComponent {

  user : User | null = null;
  opened = false;

  constructor(private authService : AuthService){
    
  }

  logout(){
    this.authService.logout();
  }
    ngOnInit(): void {
    this.user = this.authService.getUser();
  }

}
