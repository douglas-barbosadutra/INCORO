import { ThingDTO } from './ThingDTO';

export class HardwareDTO{
    idHardware: number;
    name: String;
    description: String;
    thingDTO: ThingDTO;


constructor(id: number, name: String, description: String, idThing: number ){
    this.idHardware=id;
    this.name= name;
    this.description=description;
    idThing=this.thingDTO.idThing;
}
}