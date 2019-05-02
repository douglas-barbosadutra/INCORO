import { UserDTO } from './UserDTO';

export class LabelDTO{
    idLabel: number;
    name: String;
    userDTO: UserDTO;

    constructor(id: number, name: String, userDTO: UserDTO){
        this.idLabel=id;
        this.name=name;
        this.userDTO=userDTO;
    }
}