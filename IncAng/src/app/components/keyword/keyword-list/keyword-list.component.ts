import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeywordService } from '../../../services/keyword.service';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import {LinkTKDTO} from '../../../../dto/LinkTKDTO'; 
import { LinkTKService } from '../../../services/linkTK.service';
import { ThingDTO } from '../../../../dto/ThingDTO';

@Component({
  selector: 'app-keyword-list',
  templateUrl: './keyword-list.component.html',
  styleUrls: ['./keyword-list.component.css']
})
export class KeywordListComponent implements OnInit {
  private keywordList: Array<KeywordDTO>;
  private List2: Array<ThingDTO>

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

  showTK(keywordDTO: KeywordDTO){
    this.linkTKService.showThingOfKey(keywordDTO).subscribe((data: any) => {
      if(data){
        alert(" ris " + data);
        this.List2 = data;
        //alert("ThingDTO " + this.List2 );
        //sessionStorage.setItem("list2", JSON.stringify(this.List2));
      } 
    //

    })
    
  
  }
  
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