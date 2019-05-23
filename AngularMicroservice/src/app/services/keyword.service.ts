import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { KeywordDTO } from '../../dto/KeywordDTO';

@Injectable({
  providedIn: 'root'
})
export class KeywordService {
    private keywordList: Array<KeywordDTO>;
  
    constructor(private http: HttpClient) { }

  showKeyword(){
    return this.http.get('http://localhost:8080/Keyword/showKeywords');
  }

  insertKeyword(keywordDTO: KeywordDTO){
    console.log(keywordDTO);
    return this.http.post('http://localhost:8080/Keyword/insertKeyword', keywordDTO);
  }
  
  deleteKeyword(keywordDTO: KeywordDTO){
    console.log(keywordDTO);
    return this.http.delete('http://localhost:8080/Keyword/deleteKeyword?id='+ keywordDTO.idKeyword);
  }

  updateKeyword(keywordDTO: KeywordDTO){
    console.log(keywordDTO);
    return this.http.put('http://localhost:8080/Keyword/updateKeyword', keywordDTO);
  }
}