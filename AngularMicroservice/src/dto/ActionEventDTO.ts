import { LabelDTO } from './LabelDTO';

export class ActionEventDTO{
    idActionEvent: number;
    name: String;
    description: String;
    type: number;
    label: LabelDTO;

    constructor(id: number,  description: String, name: String,label:LabelDTO, type:number){
        this.idActionEvent = id;
        this.description= description;
        this.name= name;
        this.label= label;
        this.type= type;

    }
    
}