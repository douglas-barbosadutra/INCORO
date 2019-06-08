import { Component, OnInit } from '@angular/core';
import { ActionEventDTO } from '../../../../dto/ActionEventDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UtenteDTO';
import { ActionEventService } from '../../../../app/services/action-event.service';
import { Router } from '@angular/router';
import { LabelService } from '../../../../app/services/label.service';
import { ParamDTO } from '../../../../dto/ParamDTO';

@Component({
  selector: 'app-action-event-update',
  templateUrl: './action-event-update.component.html',
  styleUrls: ['./action-event-update.component.css']
})
export class ActionEventUpdateComponent implements OnInit {
  private actionEventDTO: ActionEventDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private labelList: Array<LabelDTO>;
  private paramDTO: ParamDTO;

  constructor(private actionEventService: ActionEventService, private router: Router, private labelService: LabelService) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0, "", "", 0);
    this.labelDTO = new LabelDTO(0,"",0);
    this.actionEventDTO = JSON.parse(sessionStorage.getItem("DTOpassato")) as ActionEventDTO;
    alert("ddd " + this.actionEventDTO.label.name);
    this.labelService.showLabel(this.paramDTO).subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
      }
    })
  
  }
  updateActionEvent(){
    this.actionEventService.updateActionEvent(this.paramDTO).subscribe((data: any) => {
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
        this.router.navigateByUrl("/homeBo");
    })
  }
  

}
