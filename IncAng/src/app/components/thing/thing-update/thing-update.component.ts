import { Component, OnInit, Input } from '@angular/core';
import { ThingDTO } from '../../../../dto/ThingDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { ThingService } from '../../../../../src/app/services/thing.service';
import {LabelService} from '../../../../../src/app/services/label.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-thing-update',
  templateUrl: './thing-update.component.html',
  styleUrls: ['./thing-update.component.css']
})
export class ThingUpdateComponent implements OnInit {
  private thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private labelList: Array<LabelDTO>;
  
  constructor(private thingService: ThingService, private router: Router, private labelService: LabelService) { }


  ngOnInit() {
    //this.userDTO = new UserDTO(0,"","",0); 
    //this.thingDTO = new ThingDTO(parseInt(sessionStorage.getItem("idThing")),"","","","","","", this.userDTO, this.labelDTO);

    this.labelDTO = new LabelDTO(0,"",this.userDTO);
    this.thingDTO = JSON.parse(sessionStorage.getItem("DTOpassato")) as ThingDTO;
   
    this.labelService.showLabel().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
      }
    })
  }

  updateThing(){
  this.thingDTO.user.idUser = parseInt(sessionStorage.getItem("idUser"));
  this.thingService.updateThing(this.thingDTO).subscribe((data: any) => {
    
    if(data != null)
      alert("Inserimento effettuato");
    else
      alert("Inserimento fallito");
      this.router.navigateByUrl("/homeBO");
  })
}
}