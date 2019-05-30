import { Component, OnInit, Input } from '@angular/core';
import { LabelService } from '../../../app/services/label.service';
import { LabelDTO } from '../../../dto/LabelDTO';
import { Router } from '@angular/router';
import { ParamDTO } from '../../../dto/ParamDTO';

import { ActionEventDTO } from '../../../dto/ActionEventDTO';
import { ActionEventService } from '../../../app/services/action-event.service';
import { ThingDTO } from '../../../dto/ThingDTO';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {
  private labelList: Array<LabelDTO>;
  private listThing: Array<ThingDTO>;
  private jwt: string;
  private paramDTO: ParamDTO;
  private labelDTO: LabelDTO;
  private actionEventDTO: ActionEventDTO;
  private actionEventList: Array<ActionEventDTO>;
  messaggio: string;
  messaggio2: string;

  constructor(private labelService: LabelService, private router: Router, private actionEventService: ActionEventService) { }

  ngOnInit() {
    
   }

   findLabel(){
    this.labelDTO = new LabelDTO (0, '', 0);
    this.jwt = sessionStorage.getItem("jwt");
    this.labelDTO.name = this.messaggio;
    this.paramDTO = new ParamDTO(this.jwt, this.labelDTO);
    this.labelService.showLabelBYName(this.paramDTO).subscribe((data: Array<LabelDTO>) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
        sessionStorage.setItem("labelNavbar", JSON.stringify(this.labelList));
        }
        this.router.navigate(["homeBo/labelNavbar"]);
       //location.reload();
    }
    )}

    findAzione(){
      this.labelDTO = new LabelDTO (0, '', 0);
      this.actionEventDTO = new ActionEventDTO (0, '','',this.labelDTO,0);
      this.actionEventDTO.name = this.messaggio2;
      this.jwt = sessionStorage.getItem("jwt");
      this.paramDTO = new ParamDTO(this.jwt, this.actionEventDTO);
      // immetto una string 
      this.actionEventService.findActionByName(this.paramDTO).subscribe((data: Array<ThingDTO>) =>{
        if(data != null){
          console.log(data);
          this.listThing = data;
          sessionStorage.setItem("ThingByName", JSON.stringify(this.listThing));
          }
          this.router.navigate(["homeBo/thingNavbar"]);
      }

      )}
  }   