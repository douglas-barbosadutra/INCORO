import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KeywordDTO } from '../../../../dto/KeywordDTO';
import {LinkTKDTO} from '../../../../dto/LinkTKDTO'; 
import { LinkTKService } from '../../../services/linkTk.service';

@Component({
  selector: 'app-link-tk-show-thing',
  templateUrl: './link-tk-show-thing.component.html',
  styleUrls: ['./link-tk-show-thing.component.css']
})

export class LinkTkShowThingComponent implements OnInit {
  private linkTKDTO: LinkTKDTO;
  private keywordDTO: KeywordDTO;
  private arrayLink: Array<LinkTKDTO>;

  constructor(private router: Router, private linkTKService: LinkTKService) { }

  ngOnInit() {
    this.keywordDTO = JSON.parse(sessionStorage.getItem("keySettata")) as KeywordDTO;
    this.linkTKService.showThingOfKey(this.keywordDTO).subscribe((data: Array<LinkTKDTO>) => {
      if(data){
        this.arrayLink = data;
      }
    }
    
    )
  } 
}