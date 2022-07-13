import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = 'http://localhost:8080/users';
  private urlTable = 'http://localhost:8080/table';
  
  constructor(private http: HttpClient) { }

  getAllUser(): Observable<User[]> {
    return this.http.get<User[]>(this.url)
  }


addUser(user: User): Observable<User> {
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };
  return this.http.post<User>(this.url, user, httpOptions)
   
}


deleteUser(id: number): Observable<unknown> {
  const url = `${this.url}/${id}`; // DELETE api/heroes/42
 /*
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };*/
  return this.http.delete(url)
  
}

updateUser(data: any, id: string): Observable<any> {
  return this.http.put(`${this.url}/${id}`, data)
}

/*
public updateUser(id: string, user: User): Observable<any> {
  const body = JSON.stringify(user);

  return this.http.request('PUT', 'http://localhost:8080/users/' + id, {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    body: body
  })
}*/


/*
updateUser(id: number, user: User): Observable<User> {
  
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
   
    })
  };
  return this.http.put<User>(`${this.url}/${id}`), user, httpOptions)
}*/

getUser(id: number): Observable<User>{
  return this.http.get<User>(`${this.url}/${id}`)
}


 }
