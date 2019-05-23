import { Component, OnInit } from '@angular/core';
import { LabelDTO } from '../../../../dto/LabelDTO';
import { UserDTO } from '../../../../dto/UserDTO';
import { LabelService } from '../../../../app/services/label.service';
import { Router } from '@angular/router';
import { UserService } from '../../../../app/services/user.service';
import { ParamDTO } from '../../../../dto/ParamDTO';


@Component({
  selector: 'app-label-list',
  templateUrl: './label-list.component.html',
  styleUrls: ['./label-list.component.css']
})
export class LabelListComponent implements OnInit {
  private labelList: Array<LabelDTO>;
  private labelDTO: LabelDTO;
  private paramDTO: ParamDTO;
  private paramdeleteDTO: ParamDTO;
  private jwt: string;
  private jwtdelete: string;
  private labelDeleteDTO: LabelDTO;


  constructor(private labelService: LabelService, private router: Router, private userService: UserService) { }

  ngOnInit() {
    this.jwt = sessionStorage.getItem("jwt");
    console.log("in ngOnit arriva: " + this.jwt);
    this.paramDTO = new ParamDTO(this.jwt, this.labelDTO);
    this.labelService.showLabel(this.paramDTO).subscribe((data: Array<LabelDTO>) =>{
      if(data != null){
        console.log(data);
        this.labelList = data;
       
      }
    })
  }

  chooseLabel(idLabel: number){
    sessionStorage.setItem("idLabel", JSON.stringify(idLabel));
    this.router.navigate(["/homeBo/updateLabel"]);
  }

  setLabel(labelDTO: LabelDTO){
    alert("labelDTO" + labelDTO.name);
    sessionStorage.setItem("LabelDTOpassato", JSON.stringify(labelDTO));
    this.router.navigate(["homeBo/updateLabel"]);
  }

  deleteLabel(labelDeleteDTO: LabelDTO){
    this.labelDeleteDTO = JSON.parse(sessionStorage.getItem("LabelDTOpassato")) as LabelDTO;
    this.jwtdelete = sessionStorage.getItem("jwt");
    //console.log("in deleteLabel arriva: " + this.jwtdelete);
    this.paramdeleteDTO = new ParamDTO(this.jwtdelete, this.labelDeleteDTO);
    console.log("paramDTO in deleteLabel", this.paramdeleteDTO)

    this.labelService.deleteLabel(this.paramdeleteDTO).subscribe((data: any) =>{

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
