import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HardwareDTO } from 'src/dto/HardwareDTO';
import { ThingDTO } from 'src/dto/ThingDTO';
import { NgForm } from '@angular/forms';
import { HardwareService } from 'src/app/services/hardware.service';

@Component({
  selector: 'app-hardware-update',
  templateUrl: './hardware-update.component.html',
  styleUrls: ['./hardware-update.component.css']
})
export class HardwareUpdateComponent implements OnInit {
  private hardwareDTO: HardwareDTO;
  private thingDTO: ThingDTO

  constructor(private hardwareService: HardwareService, private router: Router) { }


  ngOnInit() {
    if(sessionStorage.getItem("idThing") == null){
      this.router.navigateByUrl("thingShow");
      alert("Devi prima selezionare una Thing");
    }
    this.hardwareDTO = new HardwareDTO(0,"", "", parseInt(sessionStorage.getItem("idThing")));
  }

  updateHardware(f: NgForm){
    console.log(this.hardwareDTO);

    this.hardwareService.updateHardware(this.hardwareDTO).subscribe((data: any) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");

        this.router.navigateByUrl("homeHardware");
    })
  }

}
