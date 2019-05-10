import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LinkTKDTO } from '../../dto/LinkTkDTO';
import { KeywordDTO } from '../../dto/KeywordDTO';

@Injectable({
  providedIn: 'root'
})
export class LinkTKService {
    private linkTKList: Array<LinkTKDTO>;
  
    constructor(private http: HttpClient) { }

    insertLinkTK(linkTKDTO: LinkTKDTO){
      console.log(linkTKDTO);
      return this.http.post('http://localhost:8080/LinkTK/insertLinkTK', linkTKDTO);
    }

    showThingOfKey(keywordDTO: KeywordDTO){
      return this.http.get('http://localhost:8080/Keyword/showThingOfKey?id='+ keywordDTO.idKeyword);
    }
}