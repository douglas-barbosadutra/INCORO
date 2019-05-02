import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { UserDTO } from 'src/dto/UserDTO';
import { LoginDTO } from 'src/dto/LoginDTO';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(result);
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  login(loginDTO: LoginDTO): Observable<UserDTO> {
    const params = new HttpParams().set('username', loginDTO.username).set('password', loginDTO.password);
    return this.http.post<UserDTO>('http://localhost:8080/User/login', params).pipe(tap((response) => 
    console.log(loginDTO.username), catchError(this.handleError("login error", {}))));
  }
}
