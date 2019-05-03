import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from 'src/dto/UserDTO';
import { ConstantPool } from '@angular/compiler';
//import { UserDTO } from 'dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  insertUser(userDTO: UserDTO){
    return this.http.post('http://localhost8080/User/insertUser', userDTO);
  }

  showUser(){
    return this.http.get('http://localhost:8080/User/showUser');
  }

  deleteUser(userDTO: UserDTO){
    console.log(userDTO);
    return this.http.delete('http://localhost:8080/User/deleteUser?id='+userDTO.idUser);
  }

}
