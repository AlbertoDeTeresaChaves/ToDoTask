import { Injectable } from '@angular/core';
import { Task } from '../../../models/task';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  taskUrl : string = "http://localhost:8080/api/tasks";

  constructor(private http: HttpClient) { }


  getAllTasks():Observable<Task[]>{

    return this.http.get<Task[]>(`${this.taskUrl}`);
  }


}
