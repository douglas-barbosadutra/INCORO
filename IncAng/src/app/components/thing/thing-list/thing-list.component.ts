import { Component, OnInit } from '@angular/core';
import { ThingDTO } from 'src/dto/ThingDTO';
import { ThingService } from 'src/app/services/thing.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-thing-list',
  templateUrl: './thing-list.component.html',
  styleUrls: ['./thing-list.component.css']
})
export class ThingListComponent implements OnInit {
  private thingList: Array<ThingDTO>;
  private thingDTO: ThingDTO;

  constructor(private thingService: ThingService, private router: Router) { }

  ngOnInit() {
    this.thingService.showThing().subscribe((data: any) =>{

      if(data != null){
        this.thingList = data;
      }
    })
  }

}
