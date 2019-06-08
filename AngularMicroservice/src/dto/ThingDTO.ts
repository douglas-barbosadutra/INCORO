import { UserDTO } from './UtenteDTO';
import { LabelDTO } from './LabelDTO';

export class ThingDTO{
    idThing: number;
    code: String;
    description: String;
    image: String;
    name: String;
    xml: String;
    protocol: String;
    idUser: number;
    label: LabelDTO;

    constructor(id:number, code: String, description: String, image: String, name: String, xml: String, protocol: String, idUser:number, labelDTO: LabelDTO){
        this.idThing=id;
        this.code=code;
        this.description=description;
        this.image=image;
        this.name=name;
        this.xml=xml;
        this.protocol=protocol;
        this.idUser=idUser;
        this.label=labelDTO;
    }
}