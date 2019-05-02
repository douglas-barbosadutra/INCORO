import { UserDTO } from './UserDTO';
import { LabelDTO } from './LabelDTO';

export class ThingDTO{
    idThing: number;
    code: String;
    image: String;
    name: String;
    xml: String;
    userDTO: UserDTO;
    labelDTO: LabelDTO;

    constructor(id:number, code: String, image: String, name: String, xml: String, userDTO: UserDTO, labelDTO: LabelDTO){
        this.idThing=id;
        this.code=code;
        this.image=image;
        this.name=name;
        this.xml=xml;
        this.userDTO=userDTO;
        this.labelDTO=labelDTO;
    }
}