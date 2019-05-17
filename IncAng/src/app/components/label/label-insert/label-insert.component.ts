import { Component, OnInit } from '@angular/core';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { Router } from '@angular/router';
import { LabelService } from '../../../../app/services/label.service';


@Component({
  selector: 'app-label-insert',
  templateUrl: './label-insert.component.html',
  styleUrls: ['./label-insert.component.css']
})
export class LabelInsertComponent implements OnInit {
  private labelDTO: LabelDTO;
  private user: UserDTO;

  constructor(private router: Router, private labelService: LabelService) { }

  ngOnInit() {
    this.user = new UserDTO(0,"","",0);
    this.labelDTO = new LabelDTO(0,"",this.user);
  }

  insertLabel(){
    //this.thingDTO.user = JSON.parse(sessionStorage.getItem("User")) as UserDTO;
    this.labelDTO.user.idUser = parseInt(sessionStorage.getItem("idUser"));
    console.log(this.labelDTO.user.idUser);
   
  this.labelService.insertLabel(this.labelDTO).subscribe((data: any) => {
    
    if(data != null)
      alert("Inserimento effettuato");
    else
      alert("Inserimento fallito");

      this.router.navigateByUrl("/homeBo");
  })
}

}
