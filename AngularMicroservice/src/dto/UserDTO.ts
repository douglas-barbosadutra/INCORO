export class UserDTO{
    idUser: number;
    username: string;
    password: string;
    type: number;


    constructor(idUser: number, username: string, password: string, type: number){
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.type = type;
    }
}
