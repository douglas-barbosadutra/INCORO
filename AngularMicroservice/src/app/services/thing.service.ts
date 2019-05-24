import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ThingDTO } from '../../dto/ThingDTO';
import { ParamDTO } from '../../dto/ParamDTO';

@Injectable({
  providedIn: 'root'
})
export class ThingService {

  constructor(private http: HttpClient) { }

  insertThing(paramDTO: ParamDTO){
    console.log("alla insert arriva",paramDTO);
    return this.http.post('http://localhost:8080/Thing/insertThing', paramDTO);
  }

  updateThing(paramDTO: ParamDTO){
    console.log(paramDTO);
    return this.http.put('http://localhost:8080/Thing/updateThing', paramDTO);
  }

  showThing(paramDTO: ParamDTO){
    console.log("alla service arriva ", paramDTO);
    return this.http.get('http://localhost:8080/Thing/showThing?jwt=' + paramDTO.jwt);
  }

  deleteThing(thingDTO: ThingDTO){
    console.log(thingDTO);
    return this.http.delete('http://localhost:8080/Thing/deleteThing?id='+thingDTO.idThing);
  }

}