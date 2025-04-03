import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl:String = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(credentials:{email:string,password:string}): Observable<any>{
    return this.http.post(`${this.apiUrl}/login`,credentials);
  }

  register(user:any){
    return this.http.post(`${this.apiUrl}/register`, user);

  }

  logout(): void {
    localStorage.removeItem('token');
  }

  isAuthenticated(): boolean{
    return !!localStorage.getItem('token');
  }
}
