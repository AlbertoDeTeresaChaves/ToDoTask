import { Component } from '@angular/core';
import { HomeService } from './home.service';
import { Task } from '../../../models/task';
import {MatCardModule} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatRippleModule} from '@angular/material/core';
import { Router } from '@angular/router';
import { CenteredButtonComponent } from "../../../core/components/centered-button/centered-button.component";
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [MatCardModule, MatButtonModule, MatTableModule, MatRippleModule, CenteredButtonComponent,NgClass],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  latestTasks: Task[]= [];

  displayedColumns : string[] = ['taskId','taskName','status','assigned']

  constructor(private homeService:HomeService, private router : Router){}

  ngOnInit():void{
    this.getLatestTaks();
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

  getLatestTaks():void{
    this.homeService.getRecentTasks().subscribe({
      next: (tasks: Task[]) =>{
        this.latestTasks = tasks;
      },
      error: (error)=>{
        console.error('No se han cargado los tasks',error)
      }
    });
  }
}
