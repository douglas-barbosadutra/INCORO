import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../../../src/app/services/user.service';
import { UserDTO } from '../../../../dto/UserDTO';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-insert',
  templateUrl: './user-insert.component.html',
  styleUrls: ['./user-insert.component.css']
})
export class UserInsertComponent implements OnInit {

  private userDTO: UserDTO;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.userDTO = new UserDTO(0,"","",0);
  }

    insertUser(){
      console.log("arrivato")
    this.userService.insertUser(this.userDTO).subscribe((data: any) => {
      console.log("arr")

      if(data != null)
        alert("Inserimento effettuato");
      else
        alert("Inserimento fallito");

        this.router.navigateByUrl("/homeAdmin");
    })
  }
}
