import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeywordService } from '../../../services/keyword.service';
import { KeywordDTO } from '../../../../dto/KeywordDTO';

@Component({
  selector: 'app-keyword-list',
  templateUrl: './keyword-list.component.html',
  styleUrls: ['./keyword-list.component.css']
})
export class KeywordListComponent implements OnInit {
  private keywordList: Array<KeywordDTO>;
  constructor(private keywordService: KeywordService, private router: Router) { }

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
