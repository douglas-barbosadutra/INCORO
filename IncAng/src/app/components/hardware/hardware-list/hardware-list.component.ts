import { Component, OnInit } from '@angular/core';
import { HardwareDTO } from '../../../../../src/dto/HardwareDTO';
import { Router } from '@angular/router';
import { HardwareService } from '../../../../../src/app/services/hardware.service';

@Component({
  selector: 'app-hardware-list',
  templateUrl: './hardware-list.component.html',
  styleUrls: ['./hardware-list.component.css']
})
export class HardwareListComponent implements OnInit {
  private hardwareList: Array<HardwareDTO>;
  private hardwareDTO: HardwareDTO;

  constructor(private hardwareService: HardwareService, private router: Router) { }

  ngOnInit() {
    this.hardwareService.showHardware().subscribe((data: any) =>{
      if(data != null){
        console.log(data)
        this.hardwareList = data;
      }
    })
  }

  deleteHardware(hardwareDTO: HardwareDTO){
    this.hardwareService.deleteHardware(hardwareDTO).subscribe((data: any) =>{
      if(data)
        alert("Cancellazione effettuata");
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeBO");
    })
  }

}
