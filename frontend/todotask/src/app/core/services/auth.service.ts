import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl:String = 'http://localhost:8080/api/auth';
  private TOKEN_KEY = 'authToken'; 
  private USER_KEY = 'authUser';

  constructor(private http: HttpClient) { }

  saveAuthUserData(token : string, user : {id:string , username: string, email:string}):void{
    localStorage.setItem(this.TOKEN_KEY,token);
    localStorage.setItem(this.USER_KEY,JSON.stringify(user));
  }

  getToken():string | null{
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUser(): User | null {
    const user = localStorage.getItem(this.USER_KEY);
    return user ? JSON.parse(user) : null;
  }

  logout(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
  }
  
  isAuthenticated(): boolean{
    return !!this.getToken();
  }

  login(credentials:{email:string,password:string}): Observable<any>{
    return this.http.post(`${this.apiUrl}/login`,credentials);
  }

  register(user:any):Observable<any>{
    return this.http.post(`${this.apiUrl}/register`, user);

  }
}
