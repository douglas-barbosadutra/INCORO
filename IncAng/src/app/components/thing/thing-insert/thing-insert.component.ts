import { Component, OnInit } from '@angular/core';
import { ThingService } from '../../../../../src/app/services/thing.service';
import {LabelService} from '../../../../../src/app/services/label.service';
import { ThingDTO } from '../../../../dto/ThingDTO';
import { Router } from '@angular/router';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';

@Component({
  selector: 'app-thing-insert',
  templateUrl: './thing-insert.component.html',
  styleUrls: ['./thing-insert.component.css']
})
export class ThingInsertComponent implements OnInit {
  private thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private labelList: Array<LabelDTO>;

  constructor(private thingService: ThingService, private router: Router, private labelService: LabelService) { }

  ngOnInit() {
    this.labelDTO = new LabelDTO(0,"",this.userDTO);
    this.thingDTO = new ThingDTO(0,"","","","","","", this.userDTO, this.labelDTO);
    
    this.labelService.showLabel().subscribe((data: any) =>{

      if(data != null){
        console.log(data);
        this.labelList = data;
      }
    })
  }

  insertThing(){
    this.thingDTO.user = JSON.parse(sessionStorage.getItem("User")) as UserDTO;
    //alert(this.labelDTO.idLabel);
    this.thingDTO.label.idLabel = this.labelDTO.idLabel;
    //alert ("asdffsa" + this.thingDTO.labelDTO.idLabel);
    //this.thingDTO.labelDTO = this.labelDTO;
    //alert("2 " + this.thingDTO.labelDTO.name);

  this.thingService.insertThing(this.thingDTO).subscribe((data: any) => {
    console.log("arr")

    if(data != null)
      alert("Inserimento effettuato");
    else
      alert("Inserimento fallito");

      this.router.navigateByUrl("/homeAdmin");
  })
}
}