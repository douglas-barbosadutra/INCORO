import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThing } from 'app/shared/model/ThingMJ/thing.model';

@Component({
    selector: 'jhi-thing-detail',
    templateUrl: './thing-detail.component.html'
})
export class ThingDetailComponent implements OnInit {
    thing: IThing;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ thing }) => {
            this.thing = thing;
        });
    }

    previousState() {
        window.history.back();
    }
}
