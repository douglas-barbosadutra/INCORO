import { Component, OnInit } from '@angular/core';
import { HardwareDTO } from 'src/dto/HardwareDTO';
import { ThingDTO } from 'src/dto/ThingDTO';
import { NgForm } from '@angular/forms';
import { Router } from "@angular/router";
import { HardwareService } from 'src/app/services/hardware.service';

@Component({
  selector: 'app-hardware-insert',
  templateUrl: './hardware-insert.component.html',
  styleUrls: ['./hardware-insert.component.css']
})
export class HardwareInsertComponent implements OnInit {
  public hardwareDTO: HardwareDTO;
  public thingDTO: ThingDTO;

  constructor(private hardwareService : HardwareService, private router: Router) { }

  ngOnInit() {
    this.hardwareDTO = new HardwareDTO(0,"","", this.thingDTO.idThing);
  }

    insertHardware(f: NgForm){
      
    this.hardwareService.insertHardware(this.hardwareDTO).subscribe((data: any) => {

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeAdmin");
    })
  }

}
