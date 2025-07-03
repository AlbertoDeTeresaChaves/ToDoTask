import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { UserService } from '../../users/user.service';
import { User } from '../../../../models/user';
import { MatOptionModule } from '@angular/material/core';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-create',
  imports: [MatInputModule,MatFormFieldModule,ReactiveFormsModule,MatIconModule,MatButtonModule,MatAutocompleteModule,MatOptionModule],
  templateUrl: './task-create.component.html',
  styleUrl: './task-create.component.css'
})
export class TaskCreateComponent {

  users:User[]=[];
  createTaskForm: FormGroup;





  constructor(private userService:UserService, private formBuilder: FormBuilder, private taskService: TaskService ){
    this.createTaskForm = formBuilder.group({
      taskName:['',[Validators.required]],
      description:['',[Validators.required]],
      assigned:['',[Validators.required]],
      status:['PENDING']
    });

  }

  ngOnInit(){
    this.getAllUsers();

  }
  
  displayFn(user: User): string {
    return user && user.username ? user.username : '';
  }

  getAllUsers():void{
    this.userService.getAllUsers().subscribe({
      next: (users: User[])=> {this.users = users;},
      error: (error)=>{
        console.error('No se han cargado los users',error)
      }
    })
  }

  onSubmit(){
    if(this.createTaskForm.valid){
      this.taskService.create(this.createTaskForm.value).subscribe({
        next: (value) => console.log('created', value) 
      });
    }
  }

}
