import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDTO } from '../../dto/UtenteDTO';
import { ConstantPool } from '@angular/compiler';
import { ParamDTO } from '../../dto/ParamDTO';
//import { UserDTO } from 'dto/UserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  findUser(id: string){
    return this.http.get('http://localhost:8094/User/findById?id='+id);
  }


  insertUser(paramDTO: ParamDTO){
    console.log(paramDTO)
    return this.http.post('http://localhost:8094/User/insertUser', paramDTO);
  }

  showUser(paramDTO: ParamDTO){
    return this.http.get('http://localhost:8094/User/showUser?jwt=' + paramDTO.jwt);
  }

  deleteUser(paramDTO: ParamDTO){
    console.log(paramDTO);
    return this.http.post('http://localhost:8094/User/deleteUser', paramDTO);
  }

  updateUser(paramDTO: ParamDTO) {
    return this.http.put( 'http://localhost:8094/User/updateUser', paramDTO);
  }

}
