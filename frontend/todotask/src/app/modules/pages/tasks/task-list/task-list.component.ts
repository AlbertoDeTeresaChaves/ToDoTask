import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { Task } from '../../../../models/task';
import { TaskService } from '../task.service';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import {MatPaginatorModule} from '@angular/material/paginator';

@Component({
  selector: 'app-task-list',
  imports: [MatCardModule,MatTableModule,NgClass, MatPaginatorModule],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {
  Tasks: Task[]= [];

  displayedColumns : string[] = ['taskId','taskName','status','assigned']

  constructor(private taskService: TaskService, private router: Router){}

  ngOnInit(): void {

    this.getAllTasks();
    
  }


  getStatusClass(status: string): string {
    switch (status) {
      case 'PENDING':
        return 'status-pending';
      case 'IN_PROGRESS':
        return 'status-in-progress';
      case 'COMPLETED':
        return 'status-completed';
      default:
        return '';
    }
  }

  navigateToTask(taskId: string):void{
    this.router.navigate(['/tasks',taskId]);
  }

  getAllTasks():void{
    this.taskService.getAllTasks().subscribe({
      next: (tasks: Task[]) =>{
        this.Tasks = tasks;
      },
      error: (error)=>{
        console.error('No se han cargado los tasks',error)
      }
    });
  }
}
