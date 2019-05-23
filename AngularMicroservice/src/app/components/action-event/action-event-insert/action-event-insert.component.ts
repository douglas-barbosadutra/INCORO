import { Component, OnInit } from '@angular/core';
import { ActionEventDTO } from '../../../../dto/ActionEventDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { ActionEventService } from '../../../../app/services/action-event.service';
import { LabelService } from '../../../../app/services/label.service';
import { Router } from '@angular/router';
import { UserDTO } from '../../../../dto/UserDTO';

@Component({
  selector: 'app-action-event-insert',
  templateUrl: './action-event-insert.component.html',
  styleUrls: ['./action-event-insert.component.css']
})
export class ActionEventInsertComponent implements OnInit {
  private actionEventDTO: ActionEventDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private labelList: Array<LabelDTO>;

  constructor(private actionEventService: ActionEventService, private labelService:LabelService, private router: Router) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","",0);
    this.labelDTO = new LabelDTO(0, "", this.userDTO);
    this.actionEventDTO = new ActionEventDTO(0, "", "", 0, this.labelDTO);
    

    this.labelService.showLabel().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
      }
    })
  
  }
  insertActionEvent(){
    console.log("qua");
    console.log(this.actionEventDTO.label.name);
   
    
    this.actionEventDTO.label.idLabel = this.labelDTO.idLabel;
   
  this.actionEventService.insertActionEvent(this.actionEventDTO).subscribe((data: any) => {
    
    if(data != null)
      alert("Inserimento effettuato");
    else
      alert("Inserimento fallito");

      this.router.navigateByUrl("/homeBo");
  })
}
}