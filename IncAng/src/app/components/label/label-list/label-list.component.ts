import { Component, OnInit } from '@angular/core';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { LabelService } from '../../../../app/services/label.service';
import { Router } from '@angular/router';
import { UserService } from '../../../../app/services/user.service';


@Component({
  selector: 'app-label-list',
  templateUrl: './label-list.component.html',
  styleUrls: ['./label-list.component.css']
})
export class LabelListComponent implements OnInit {
  private labelList: Array<LabelDTO>;
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;

  constructor(private labelService: LabelService, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.labelService.showLabel().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
        console.log(data);
      }
    })
  }

  chooseLabel(idLabel: number){
    sessionStorage.setItem("idLabel", JSON.stringify(idLabel));
    this.router.navigateByUrl("/updateLabel");
  }

  deleteLabel(labelDTO: LabelDTO){

    this.labelService.deleteLabel(labelDTO).subscribe((data: any) =>{

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
