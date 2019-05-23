import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActionEventDTO } from '../../dto/ActionEventDTO';

@Injectable({
  providedIn: 'root'
})
export class ActionEventService {

  constructor(private http: HttpClient) { }
    insertActionEvent(actionEventDTO: ActionEventDTO){
      console.log(actionEventDTO.label.idLabel);
      return this.http.post('http://localhost:8080/ActionEvent/insertActionEvent', actionEventDTO);
    }
  
    updateActionEvent(actionEventDTO: ActionEventDTO){
      console.log(actionEventDTO);
      return this.http.put('http://localhost:8080/ActionEvent/updateActionEvent', actionEventDTO);
    }
  
    showActionEvent(){
      return this.http.get('http://localhost:8080/ActionEvent/showActionEvent');
    }
  
    deleteActionEvent(actionEventDTO: ActionEventDTO){
      console.log(actionEventDTO);
      return this.http.delete('http://localhost:8080/ActionEvent/deleteActionEvent?id='+actionEventDTO.idActionEvent);
    }
  }

