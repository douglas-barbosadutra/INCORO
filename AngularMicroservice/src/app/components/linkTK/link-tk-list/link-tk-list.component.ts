import { Component, OnInit } from '@angular/core';
import { LinkTKDTO } from '../../../../dto/LinkTkDTO';
import { Router } from '@angular/router';
import { LinkTKService } from '../../../../app/services/linkTk.service';

@Component({
  selector: 'app-link-tk-list',
  templateUrl: './link-tk-list.component.html',
  styleUrls: ['./link-tk-list.component.css']
})
export class LinkTkListComponent implements OnInit {
  private linkTKList : Array<LinkTKDTO>;
  private linkTKDTO : LinkTKDTO;

  constructor(private router:Router, private linkTKService: LinkTKService) {
  }

  ngOnInit() {
    this.linkTKService.showLinkTK().subscribe((data: any) =>{
      if(data != null){
        console.log(data);
        this.linkTKList = data;
      }
    })
  }

  chooseLinkTK(idLinkTK: number){
    sessionStorage.setItem("idLinkTK", JSON.stringify(idLinkTK));
    this.router.navigateByUrl("/updateLinkTK");
  }

  setDTO(linkTK: LinkTKDTO){
    sessionStorage.setItem("DTOpassato", JSON.stringify(linkTK));
    this.router.navigateByUrl("/updateLinkTK");
  }

  deleteLinkTK(linkTKDTO: LinkTKDTO){
    console.log(linkTKDTO);
    this.linkTKService.deleteLinkTK(linkTKDTO).subscribe((data: any) =>{

      if(data){
        alert("Cancellazione effettuata");
        location.reload(true);
      }
        
      else
        alert("Cancellazione fallita");

      this.router.navigateByUrl("homeBo");
    })
  }

}
