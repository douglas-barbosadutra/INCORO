export interface IActionevent {
    id?: string;
    name?: string;
    description?: string;
    type?: number;
}

export class Actionevent implements IActionevent {
    constructor(public id?: string, public name?: string, public description?: string, public type?: number) {}
}
