import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../../../src/app/services/user.service';
import { UserDTO } from '../../../../dto/UtenteDTO';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ParamDTO } from '../../../../dto/ParamDTO';

@Component({
  selector: 'app-user-insert',
  templateUrl: './user-insert.component.html',
  styleUrls: ['./user-insert.component.css']
})
export class UserInsertComponent implements OnInit {

  private userDTO: UserDTO;
  private paramDTO: ParamDTO;
  private jwt: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    console.log(this.jwt);
    
    this.userDTO = new UserDTO(0,"","",0);
  }

    insertUser(){
      this.jwt= sessionStorage.getItem("jwt");
      this.paramDTO = new ParamDTO(this.jwt, this.userDTO);
      console.log("user: ",this.userDTO);
      this.userService.insertUser(this.paramDTO).subscribe((data: any) => {
      console.log("arr")

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeAdmin");
    })
  }
}
