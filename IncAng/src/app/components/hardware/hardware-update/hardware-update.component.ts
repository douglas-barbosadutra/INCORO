import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HardwareDTO } from '../../../../../src/dto/HardwareDTO';
import { ThingDTO } from '../../../../../src/dto/ThingDTO';
import { NgForm } from '@angular/forms';
import { HardwareService } from '../../../../../src/app/services/hardware.service';
import { ThingService } from '../../../../../src/app/services/thing.service';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { LinkTKDTO } from '../../../../dto/LinkTKDTO';

@Component({
  selector: 'app-hardware-update',
  templateUrl: './hardware-update.component.html',
  styleUrls: ['./hardware-update.component.css']
})
export class HardwareUpdateComponent implements OnInit {
  private hardwareDTO: HardwareDTO;
  private thingDTO: ThingDTO;
  private keywordDTO: KeywordDTO;
  private userDTO: UserDTO;
  private labelDTO: LabelDTO;
  private linkTKDTO: LinkTKDTO;
  private thingList: Array<ThingDTO>;

  constructor(private hardwareService: HardwareService, private router: Router, private thingService: ThingService) { }

  ngOnInit() {
    
    this.labelDTO = new LabelDTO(0,"",this.userDTO);
    this.userDTO = new UserDTO(0,"","",0);
    this.thingDTO = new ThingDTO(0,"","","","","","", this.userDTO, this.labelDTO);
    //this.hardwareDTO = new HardwareDTO(0,"","",false,this.thingDTO);*/
    this.hardwareDTO = JSON.parse(sessionStorage.getItem("DTOpassato")) as HardwareDTO;

    this.thingService.showThing().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.thingList = data;
      }
    })
  }

  aggiornaHardware(){
    //this.hardwareDTO = JSON.parse(sessionStorage.getItem("DTOpassato")) as HardwareDTO;   
    
    this.hardwareService.updateHardware(this.hardwareDTO).subscribe((data: any) => {
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Collegamento già esistente");
      this.router.navigateByUrl("/listHardware");
    })
  }

}
