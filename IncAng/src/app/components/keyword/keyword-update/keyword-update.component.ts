import { Component, OnInit } from '@angular/core';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import {KeywordService} from '../../../../../src/app/services/keyword.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-keyword-update',
  templateUrl: './keyword-update.component.html',
  styleUrls: ['./keyword-update.component.css']
})
export class KeywordUpdateComponent implements OnInit {
  private keywordDTO: KeywordDTO;
  private keywordList: Array<KeywordDTO>

  constructor(private keywordService: KeywordService, private router: Router,) { }

  ngOnInit() {
    this.keywordDTO = new KeywordDTO(0,"");

    this.keywordService.showKeyword().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.keywordList = data;
      }
    })
  }

  updateKeyword(){
    this.keywordDTO.idKeyword = parseInt(sessionStorage.getItem("idKeyword"));
    this.keywordService.updateKeyword(this.keywordDTO).subscribe((data: any) => {
      
      if(data != null){
        alert("Aggionamento effettuato");
        this.router.navigateByUrl("/homeBO");
      }
      else{
        alert("Aggiornamento fallito");
        this.router.navigateByUrl("/homeBO");
    }
    })
}
}