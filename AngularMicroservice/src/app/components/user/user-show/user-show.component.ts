import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../../../../dto/UtenteDTO';
import { UserService } from '../../../../../src/app/services/user.service';
import { Router } from '@angular/router';
import { ParamDTO } from '../../../../dto/ParamDTO';
import { UserLoggedDTO } from '../../../../dto/UserLoggedDTO';

@Component({
  selector: 'app-user-show',
  templateUrl: './user-show.component.html',
  styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {
  private userList: Array<UserDTO>;
  private userDTO: UserDTO;
  private paramDTO: ParamDTO;
  private jwt: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    //alert("prima di prelevare: " + this.jwt);
    this.jwt = sessionStorage.getItem("jwt");
    //alert("nel show: " + this.jwt);
    this.paramDTO = new ParamDTO(this.jwt, this.userDTO);
    //alert("paramDTO nel show: " + this.paramDTO);

    this.userService.showUser(this.paramDTO).subscribe((data: Array<UserDTO>) =>{
      //alert(" param " + this.paramDTO);
      console.log("user: ",data)
     //alert("nel show: " + this.jwt);
     // alert(" jwy " + this.jwt);
      if(data != null){
        this.userList = data;
      }
    })
  }

  chooseUser(idUser: number){
    sessionStorage.setItem("idUser", JSON.stringify(idUser));
    this.router.navigateByUrl("/updateUser");
  } 
  deleteUser(userDTO: UserDTO){
    alert("param:" + this.jwt);

    this.userService.deleteUser(this.paramDTO).subscribe((data: any) =>{
      //alert("param:" + this.jwt);

      if(data){
        alert("Cancellazione effettuata");
        location.reload(true);
      }
        
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeAdmin");
    })
  }

}
