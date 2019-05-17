import { Component, OnInit } from '@angular/core';
import { ThingDTO } from '../../../../dto/ThingDTO';
import { ThingService } from '../../../../../src/app/services/thing.service';

import { KeywordService } from '../../../../../src/app/services/keyword.service';
import { LinkTKService } from '../../../../../src/app/services/linkTk.service';
import { Router } from '@angular/router';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { LinkTKDTO} from '../../../../dto/LinkTKDTO';
import { KeywordDTO } from '../../../../dto/KeywordDTO';

@Component({
  selector: 'app-link-tk-insert',
  templateUrl: './link-tk-insert.component.html',
  styleUrls: ['./link-tk-insert.component.css']
})
export class LinkTKInsertComponent implements OnInit {
  private keywordList: Array<KeywordDTO>
  private thingList: Array<ThingDTO>
  private thingDTO: ThingDTO;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  private keywordDTO: KeywordDTO;
  private linkTkDTO: LinkTKDTO;

  constructor(private thingService: ThingService, private keywordService: KeywordService, private linkTKService: LinkTKService, private router: Router) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","",0);
    this.labelDTO = new LabelDTO(0,"",this.userDTO);
    this.thingDTO = new ThingDTO(0,"","","","","","", this.userDTO, this.labelDTO);
    this.keywordDTO = new KeywordDTO(0,"");
    this.linkTkDTO = new LinkTKDTO(0,this.thingDTO, this.keywordDTO);

    this.thingService.showThing().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.thingList = data;
      }
    })

    this.keywordService.showKeyword().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.keywordList = data;
      }
  })
  }

  insertLinkTK(){
    this.linkTkDTO.thing.idThing = this.thingDTO.idThing;
    this.linkTkDTO.keyword.idKeyword = this.keywordDTO.idKeyword;
    
    this.linkTKService.insertLinkTK(this.linkTkDTO).subscribe((data: any) => {
    
      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Collegamento già esistente");
  
        this.router.navigateByUrl("/homeBo");
    })



  }
}