import { LabelDTO } from './LabelDTO';

export class ActionEventDTO{
    idActionEvent: number;
    name: String;
    description: String;
    type: number;
    label: LabelDTO;

    constructor(id: number, name: String, description, type:number, labelDTO:LabelDTO){
        this.idActionEvent = id;
        this.name= name;
        this.description= description;
        this.type= type;
        this.label= labelDTO;

    }
    
}