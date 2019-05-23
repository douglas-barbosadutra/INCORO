import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ThingDTO } from '../../dto/ThingDTO';

@Injectable({
  providedIn: 'root'
})
export class ThingService {

  constructor(private http: HttpClient) { }

  insertThing(thingDTO: ThingDTO){
    console.log(thingDTO);
    return this.http.post('http://localhost:8080/Thing/insertThing', thingDTO);
  }

  updateThing(thingDTO: ThingDTO){
    console.log(thingDTO);
    return this.http.put('http://localhost:8080/Thing/updateThing', thingDTO);
  }

  showThing(){
    return this.http.get('http://localhost:8080/Thing/showThing');
  }

  deleteThing(thingDTO: ThingDTO){
    console.log(thingDTO);
    return this.http.delete('http://localhost:8080/Thing/deleteThing?id='+thingDTO.idThing);
  }

}