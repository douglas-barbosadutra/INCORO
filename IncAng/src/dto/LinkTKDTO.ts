import { ThingDTO } from './ThingDTO';
import { KeywordDTO } from './KeywordDTO';

export class LinkTKDTO {
    idLink: number;
    thing: ThingDTO;
    keyword: KeywordDTO;
    
    constructor(id:number, thing: ThingDTO, keyword: KeywordDTO){
        this.idLink = id;
        this.thing = thing;
        this.keyword = keyword;
    }
}