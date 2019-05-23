import { Component, OnInit } from '@angular/core';
import { ActionEventDTO } from '../../../../dto/ActionEventDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { ActionEventService } from '../../../../app/services/action-event.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-action-event-list',
  templateUrl: './action-event-list.component.html',
  styleUrls: ['./action-event-list.component.css']
})
export class ActionEventListComponent implements OnInit {
  private actionEventList: Array<ActionEventDTO>;
  private actionEventDTO: ActionEventDTO;
  private labelDTO: LabelDTO;

  constructor(private actionEventService: ActionEventService, private router: Router) { }

  ngOnInit() {
    this.actionEventService.showActionEvent().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.actionEventList = data;
      }
    })
  
  }
  chooseActionEvent(idActionEvent: number){
    sessionStorage.setItem("idActionEvent", JSON.stringify(idActionEvent));
    this.router.navigate(["/homeBo/updateActionEvent"]);

  }



  setDTO(actionEvent: ActionEventDTO){
    sessionStorage.setItem("DTOpassato", JSON.stringify(actionEvent));
    this.router.navigate(["/homeBo/updateActionEvent"]);
  }

  deleteActionEvent(actionEventDTO: ActionEventDTO){

    this.actionEventService.deleteActionEvent(actionEventDTO).subscribe((data: any) =>{

      if(data){
        alert("Cancellazione effettuata");
        location.reload(true);
      }

      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeBo");
    })
  }

}
