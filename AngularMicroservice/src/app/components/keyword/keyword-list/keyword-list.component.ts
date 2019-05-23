import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeywordService } from '../../../services/keyword.service';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import {LinkTKDTO} from '../../../../dto/LinkTKDTO';
import { LinkTKService } from '../../../services/linkTk.service';
import { ThingDTO } from '../../../../dto/ThingDTO';

@Component({
  selector: 'app-keyword-list',
  templateUrl: './keyword-list.component.html',
  styleUrls: ['./keyword-list.component.css']
})

export class KeywordListComponent implements OnInit {
  private keywordList: Array<KeywordDTO>;
  private arrayThingDTO: Array<ThingDTO>;
  private arrayLink: Array<LinkTKDTO>;
  private arrayLinkSession: Array<LinkTKDTO>;
  private thingDTO: ThingDTO;

  private nomeKey: String;

  constructor(private keywordService: KeywordService, private router: Router, private linkTKService: LinkTKService) { }

  ngOnInit() {
    this.keywordService.showKeyword().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.keywordList = data;
      }
    })
  }

  chooseKeyword(idKeyword: number){
    sessionStorage.setItem("idKeyword", JSON.stringify(idKeyword));
    this.router.navigateByUrl("/updateKeyword");
  }

  setDTO(keywordDTO: KeywordDTO){
    sessionStorage.setItem("KeywordDTOpassato", JSON.stringify(keywordDTO));
    this.router.navigateByUrl("/updateKeyword")
  }

  showTK2(keywordDTO: KeywordDTO){
    sessionStorage.setItem("keySettata", JSON.stringify(keywordDTO));
    this.router.navigateByUrl("/showLinkThing");
  }

  /*
  showTK(keywordDTO: KeywordDTO){
    // del seguente metodo ci arriva un array di LinkTKDTO non un array di ThingDTO.
    this.linkTKService.showThingOfKey(keywordDTO).subscribe((data: Array<LinkTKDTO>) => {
      if(data){
        this.arrayLink = data;

        sessionStorage.setItem("arrayLink", JSON.stringify(this.arrayLink));
        this.arrayLinkSession = JSON.parse(sessionStorage.getItem("arrayLink")) as Array<LinkTKDTO>;

        //console.log("arrayLinkSession: ", this.arrayLinkSession);

        for (var i=0; i<this.arrayLinkSession.length; i++) {
            this.thingDTO = this.arrayLinkSession[i].thing;
            this.nomeKey = this.arrayLinkSession[i].keyword.name;
            alert("La Keyword: " + this.nomeKey  + " ha associata la Thing: " + this.thingDTO.name);
        }
      }
    }
    )
  }*/

  deleteKeyword(keywordDTO: KeywordDTO){

    this.keywordService.deleteKeyword(keywordDTO).subscribe((data: any) =>{
      if(data){
        alert("Cancellazione effettuata");
        location.reload(true);
      }
      else
        alert("Cancellazione fallita");
      this.router.navigateByUrl("homeBO");
    })
  }

}
