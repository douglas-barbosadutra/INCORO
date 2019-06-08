import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { UserDTO } from '../../dto/UtenteDTO';
import { LoginDTO } from '../../../src/dto/LoginDTO';
import {  GoogleLoginProvider } from "angularx-social-login";
import { SocialUser } from 'angularx-social-login';
import { UserLoggedDTO } from '../../dto/UserLoggedDTO';

@Injectable({
  providedIn: 'root'
  
})
export class LoginService {
  private wrkUser : LoginDTO;
  constructor(private http: HttpClient) {
    this.wrkUser = new LoginDTO("","");
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log(result);
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  login(loginDTO:LoginDTO) {
    console.log("login alla service: ", loginDTO);
    return this.http.post('http://localhost:8080/UserMJ/api/loginDTO', loginDTO).pipe(tap((response) =>
    console.log(loginDTO.username), catchError(this.handleError("login error", {}))));
  }

  googleLogin(socialUser : SocialUser) : Observable<UserDTO> {

    this.wrkUser.username = socialUser.name;
    this.wrkUser.password = socialUser.email;
    return this.http.post<UserDTO>('//localhost:8080/User/loginGoogle',this.wrkUser).pipe(tap((response)=>
    console.log(socialUser.name),catchError(this.handleError("login error2",{}))));
  }
}
