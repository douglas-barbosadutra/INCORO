import { Component, OnInit } from '@angular/core';
import {KeywordService} from '../../../../../src/app/services/keyword.service';
import { ThingService } from '../../../../../src/app/services/thing.service';
import { Router } from '@angular/router';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import { ThingDTO } from '../../../../dto/ThingDTO';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { UserService } from '../../../../app/services/user.service';
import { LabelService } from '../../../../app/services/label.service';
import { NgModel } from '@angular/forms';
import { ThingListComponent } from '../../../../app/components/thing/thing-list/thing-list.component';

@Component({
  selector: 'app-keyword-insert',
  templateUrl: './keyword-insert.component.html',
  styleUrls: ['./keyword-insert.component.css']
})
export class KeywordInsertComponent implements OnInit {
  private keywordDTO: KeywordDTO;
  private thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private thingList: Array<ThingDTO>;

  constructor(private thingService: ThingService, private router: Router, private keywordService: KeywordService, private userService: UserService, private labelService: LabelService) { }

  ngOnInit() {
    //this.userDTO = new UserDTO(0,"","",0);
    //this.labelDTO = new LabelDTO(0,"",this.userDTO);
    //this.thingDTO = new ThingDTO(0,"","","","","","", this.userDTO, this.labelDTO);
    this.keywordDTO = new KeywordDTO(0,"");
    
    this.thingService.showThing().subscribe((data: any) => {
        if(data != null) {
          console.log(data);
          this.thingList = data;
        }
    });
    
  }

  insertKeyword(){ 
     //this.keywordDTO.thing = JSON.parse(sessionStorage.getItem("listaOfDTO")) as Array<ThingDTO>;
     this.keywordService.insertKeyword(this.keywordDTO).subscribe((data: any) => {
      
       if(data != null)
         alert("Inserimento effettuato");
       else
         alert("Inserimento fallito");
         this.router.navigateByUrl("/homeBO");
    })
   } 
}