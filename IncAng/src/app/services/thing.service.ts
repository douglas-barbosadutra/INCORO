import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ThingService {

  constructor(private http: HttpClient) { }

  showThing(){
    return this.http.get('http://localhost:8080/Thing/showThing');
  }
}
