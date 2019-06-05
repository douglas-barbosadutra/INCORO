export interface IAsdf {
    id?: string;
    asdxc?: string;
}

export class Asdf implements IAsdf {
    constructor(public id?: string, public asdxc?: string) {}
}
