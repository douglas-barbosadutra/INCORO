import { Component, OnInit } from '@angular/core';
import { HardwareDTO } from '../../../../../src/dto/HardwareDTO';
import { ThingDTO } from '../../../../../src/dto/ThingDTO';
import { NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { HardwareService } from '../../../../../src/app/services/hardware.service';
import {ThingService } from '../../../../../src/app/services/thing.service';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';

@Component({
  selector: 'app-hardware-insert',
  templateUrl: './hardware-insert.component.html',
  styleUrls: ['./hardware-insert.component.css']
})
export class HardwareInsertComponent implements OnInit {
  public hardwareDTO: HardwareDTO;
  public thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private thingList: Array<ThingDTO>

  constructor(private hardwareService : HardwareService, private router: Router, private thingService: ThingService) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","",0);
    this.labelDTO = new LabelDTO(0,"",this.userDTO);
    this.thingDTO = new ThingDTO(0,"","","","","","", this.userDTO, this.labelDTO);

    this.hardwareDTO = new HardwareDTO(0,"","", this.thingDTO);
    this.thingService.showThing().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.thingList = data;
      }
    })
  }

    insertHardware(){
    this.hardwareService.insertHardware(this.hardwareDTO).subscribe((data: any) => {
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");
        this.router.navigateByUrl("/homeBO");
    })
  }
}