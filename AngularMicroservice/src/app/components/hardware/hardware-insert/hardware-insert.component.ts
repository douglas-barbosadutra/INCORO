import { Component, OnInit } from '@angular/core';
import { HardwareDTO } from '../../../../../src/dto/HardwareDTO';
import { ThingDTO } from '../../../../../src/dto/ThingDTO';
import { NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { HardwareService } from '../../../../../src/app/services/hardware.service';
import {ThingService } from '../../../../../src/app/services/thing.service';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { ParamDTO } from '../../../../dto/ParamDTO';

@Component({
  selector: 'app-hardware-insert',
  templateUrl: './hardware-insert.component.html',
  styleUrls: ['./hardware-insert.component.css']
})
export class HardwareInsertComponent implements OnInit {
  public hardwareDTO: HardwareDTO;
  public thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private thingList: Array<ThingDTO>
  private paramDTO: ParamDTO;

  constructor(private hardwareService : HardwareService, private router: Router, private thingService: ThingService) { }

  ngOnInit() {
    this.labelDTO = new LabelDTO(0,"",0);
    this.thingDTO = new ThingDTO(0,"","","","","","", 0, this.labelDTO);

    this.hardwareDTO = new HardwareDTO(0,"","", false,this.thingDTO);
    this.thingService.showThing(this.paramDTO).subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.thingList = data;
      }
    })
  }

    insertHardware(){
    console.log(this.hardwareDTO.master);
    this.hardwareService.insertHardware(this.hardwareDTO).subscribe((data: any) => {
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
        this.router.navigateByUrl("/homeBo");
    })
  }
}