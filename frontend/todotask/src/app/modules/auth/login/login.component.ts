import { Component, signal } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { DialogService } from '../../../core/services/dialog.service';

@Component({
  selector: 'app-login',
  imports: [MatInputModule,MatFormFieldModule,ReactiveFormsModule,MatIconModule,MatButtonModule,MatDividerModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm: FormGroup;
  hide = signal(true);
  
  constructor(private formBuilder: FormBuilder, private authService: AuthService,private router :Router, private dialogService : DialogService){
    this.loginForm = this.formBuilder.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(10)]]
    });
  }

  
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }


  onSubmit(){
    if(this.loginForm.valid){
      this.authService.login(this.loginForm.value).subscribe({
        next: () => {this.router.navigate(['home'])} ,
        error: () => this.dialogService.openDialog('TENEMOS UN PROBLEMA','Usuario o contraseña incorrecto','assets/images/confused-person.jpg','300px','400px')
      });

    }
  }
}
