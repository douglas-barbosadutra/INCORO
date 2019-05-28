import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../../../../dto/UserDTO';
import { Router } from '@angular/router';
import { UserService } from '../../../../../src/app/services/user.service';
import { ParamDTO } from '../../../../dto/ParamDTO';


@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  public userDTO: UserDTO;
  private paramDTO: ParamDTO;

  constructor(private router: Router, private userSerivce: UserService) { }

  ngOnInit() {
    //this.findUser();
    this.userDTO = new UserDTO(parseInt(sessionStorage.getItem("idUser")),"","",0);
  }

  updateUser(){
    console.log(this.userDTO);

    this.userSerivce.updateUser(this.paramDTO).subscribe((data: any) => {

      if(data != null)
        alert("Aggiornamento effettuato");
      else
        alert("Aggiornamento fallito");

        this.router.navigateByUrl("homeAdmin");
    })
  }
}
