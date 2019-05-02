import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  insertUser(userDTO: UserDTO){
    return this.http.post('http.localhost8080/User/insertUser', userDTO);
  }

}
