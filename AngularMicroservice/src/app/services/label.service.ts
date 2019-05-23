import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LabelDTO } from '../../../src/dto/LabelDTO';

@Injectable({
  providedIn: 'root'
})
export class LabelService {

  constructor(private http: HttpClient) { }
  
  //qui andra paramDTO
  insertLabel(labelDTO: LabelDTO){
    console.log("prova" + labelDTO.user.idUser);
    return this.http.post('http://localhost:8080/Label/insertLabel', labelDTO);
  }

  //qui andrà l'oggetto paramDTO.. sempre con la post
  showLabel(){
    return this.http.get('http://localhost:8080/Label/showLabel');
  }

  //anche qui andrà l'oggetto.. si utilizzerà POST
  deleteLabel(labelDTO: LabelDTO){
    console.log(labelDTO.idLabel);
    return this.http.delete('http://localhost:8080/Label/deleteLabel?id='+ labelDTO.idLabel);
  }
  
  updateLabel(labelDTO: LabelDTO) {
    console.log(labelDTO);
    return this.http.put('http://localhost:8080/Label/updateLabel', labelDTO);
  }

  showActionByLabel(labelDTO: LabelDTO){
    return this.http.get('http://localhost:8080/ActionEvent/showActionByLabel?id=' + labelDTO.idLabel);
  }

}
