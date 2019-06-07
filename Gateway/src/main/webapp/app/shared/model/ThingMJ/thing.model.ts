export interface IThing {
    id?: string;
    name?: string;
    description?: string;
}

export class Thing implements IThing {
    constructor(public id?: string, public name?: string, public description?: string) {}
}
