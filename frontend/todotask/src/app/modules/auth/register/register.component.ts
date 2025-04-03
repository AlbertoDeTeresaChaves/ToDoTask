import { Component, signal } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
@Component({
  selector: 'app-register',
  imports: [MatInputModule,MatFormFieldModule,ReactiveFormsModule,MatIconModule,MatButtonModule,MatDividerModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm : FormGroup;
  hide = signal(true);

  constructor(private formBuilder: FormBuilder,private authService: AuthService, private router: Router){
    this.registerForm = formBuilder.group({
      username: ['',Validators.required],
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(10)]]
    })
  }

  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }


  onSubmit(){
    if(this.registerForm.valid){
      this.authService.register(this.registerForm.value).subscribe({
        next: (response) => {this.router.navigate(['home']), console.log('Register exitoso',response)},
        error: (err) => console.error('Register erroneo',err)
      });
    }

    
  }
}
