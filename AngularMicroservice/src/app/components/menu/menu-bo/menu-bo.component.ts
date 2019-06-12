import { Component, OnInit } from '@angular/core';
import { ThingDTO } from '../../../../dto/ThingDTO';
import { ThingService } from '../../../services/thing.service';
import { Router } from '@angular/router';
import { ParamDTO } from '../../../../dto/ParamDTO';
import { UtenteDTO } from '../../../../dto/UtenteDTO';
import { UtenteService } from '../../../services/utente.service';
@Component({
  selector: 'app-menu-bo',
  templateUrl: './menu-bo.component.html',
  styleUrls: ['./menu-bo.component.css']
})

export class MenuBoComponent implements OnInit {
  private thingList: Array<ThingDTO>;
  private thingDTO: ThingDTO;
  private jwt: string;
  private paramDTO: ParamDTO;
  private userLog: UtenteDTO;

  constructor(private thingService: ThingService, private router: Router ,private utenteService : UtenteService) { }

  ngOnInit() {
    this.jwt = sessionStorage.getItem("jwt");
    //this.userService.findUser(this.jwt).subscribe((data: any)=>{
    //  if(data !=null){
    //    this.userLog = data;
    //  }
    //});
    console.log("in ngOnit arriva: " + this.jwt);
    this.paramDTO = new ParamDTO(this.jwt, this.thingDTO);
    this.thingService.showThing(this.paramDTO).subscribe((data: Array<ThingDTO>) =>{
      if(data != null){
        console.log("XSXSXSXSXSXSXSXSXS");
        console.log(data);
        this.thingList = data;
      }
    });
  }

}
