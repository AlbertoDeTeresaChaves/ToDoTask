import { Routes } from '@angular/router';
import { HomeComponent } from './modules/pages/home/home.component';
import { LoginComponent } from './modules/auth/login/login.component';
import { RegisterComponent } from './modules/auth/register/register.component';
import { AuthScreenComponent } from './modules/auth/auth-screen/auth-screen.component';
import { MainScreenComponent } from './modules/main-screen/main-screen.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: '',
    component: MainScreenComponent,
    children: [{ path: 'home', component: HomeComponent }],
  },
  {
    path: '',
    component: AuthScreenComponent,
    children: [
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: '', redirectTo: 'login', pathMatch: 'full' },
    ],
  },
  { path: '**', redirectTo: 'login' },
];
