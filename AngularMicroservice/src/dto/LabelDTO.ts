import { UserDTO } from './UserDTO';

export class LabelDTO{
    idLabel: number;
    name: String;
    user: UserDTO;

    constructor(id: number, name: String, user: UserDTO){
        this.idLabel=id;
        this.name=name;
        this.user=user;
    }
}