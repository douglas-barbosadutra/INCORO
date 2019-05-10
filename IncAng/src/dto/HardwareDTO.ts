import { ThingDTO } from './ThingDTO';

export class HardwareDTO{
    idHardware: number;
    name: String;
    description: String;
    thing: ThingDTO;


constructor(id: number, name: String, description: String, thingDTO: ThingDTO ){
    this.idHardware=id;
    this.name= name;
    this.description=description;
    this.thing = thingDTO;
}
}