import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Task } from '../../../models/task';

@Injectable({
  providedIn: 'root'
})
export class HomeService {
  private apiUrl = 'http://localhost:8080/api/tasks';
  constructor(private http: HttpClient) { }

  getRecentTasks():Observable<Task[]>{
    return this.http.get<Task[]>(`${this.apiUrl}/latest`);
  }
}
