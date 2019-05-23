import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../../../src/dto/UserDTO';
import { ConstantPool } from '@angular/compiler';
//import { UserDTO } from 'dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  
  insertUser(userDTO: UserDTO){
    console.log(userDTO)
    return this.http.post('http://localhost:8094/User/insertUser', userDTO);
  }

  showUser(){
    return this.http.get('http://localhost:8094/User/showUser');
  }

  deleteUser(userDTO: UserDTO){
    console.log(userDTO);
    return this.http.delete('http://localhost:8094/User/deleteUser?id='+userDTO.idUser);
  }
  
  updateUser(userDTO: UserDTO) {
    return this.http.put( 'http://localhost:8094/User/updateUser', userDTO);
  }

}
