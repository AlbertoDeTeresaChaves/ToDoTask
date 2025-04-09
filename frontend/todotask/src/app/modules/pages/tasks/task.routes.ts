import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskDetailComponent } from './task-detail/task-detail.component';

export const routes: Routes = [
    {path:'',component:TaskListComponent},
    {path:':id',component:TaskDetailComponent}
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TasksRoutingModule {}