import { Component, OnInit } from '@angular/core';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { Router } from '@angular/router';
import { LabelService } from '../../../../app/services/label.service';


@Component({
  selector: 'app-label-update',
  templateUrl: './label-update.component.html',
  styleUrls: ['./label-update.component.css']
})
export class LabelUpdateComponent implements OnInit {
  private labelDTO: LabelDTO;
  private userDTO: UserDTO;
  

  constructor(private router: Router, private labelService: LabelService) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","",0);
    this.labelDTO = JSON.parse(sessionStorage.getItem("LabelDTOpassato")) as LabelDTO;
    //this.labelDTO = new LabelDTO(parseInt(sessionStorage.getItem("idLabel")),"",this.userDTO);
    
  }

  updateLabel(){
    this.labelDTO.user.idUser = parseInt(sessionStorage.getItem("idUser"));
    this.labelService.updateLabel(this.labelDTO).subscribe((data: LabelDTO) => {
      
      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Label già esistente");

      this.router.navigateByUrl("/homeBo");
    })
  }

}
