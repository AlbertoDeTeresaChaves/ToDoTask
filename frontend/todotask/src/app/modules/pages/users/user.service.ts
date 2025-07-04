import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string = "http://localhost:8080/api/users";

  constructor(private http:HttpClient) { }


  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(`${this.userUrl}`);
  }
}
