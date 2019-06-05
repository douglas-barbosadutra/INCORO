export interface IUtente {
    id?: string;
    username?: string;
    password?: string;
    type?: number;
}

export class Utente implements IUtente {
    constructor(public id?: string, public username?: string, public password?: string, public type?: number) {}
}
