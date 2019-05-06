import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../../../../dto/UserDTO';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-show',
  templateUrl: './user-show.component.html',
  styleUrls: ['./user-show.component.css']
})
export class UserShowComponent implements OnInit {
  private userList: Array<UserDTO>;
  private userDTO: UserDTO;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userService.showUser().subscribe((data: any) =>{

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

    this.userService.deleteUser(userDTO).subscribe((data: any) =>{

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
