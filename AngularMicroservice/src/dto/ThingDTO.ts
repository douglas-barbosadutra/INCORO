import { UserDTO } from './UserDTO';
import { LabelDTO } from './LabelDTO';

export class ThingDTO{
    idThing: number;
    code: String;
    description: String;
    image: String;
    name: String;
    xml: String;
    protocol: String;
    user: UserDTO;
    label: LabelDTO;

    constructor(id:number, code: String, description: String, image: String, name: String, xml: String, protocol: String, userDTO: UserDTO, labelDTO: LabelDTO){
        this.idThing=id;
        this.code=code;
        this.description=description;
        this.image=image;
        this.name=name;
        this.xml=xml;
        this.protocol=protocol;
        this.user=userDTO;
        this.label=labelDTO;
    }
}