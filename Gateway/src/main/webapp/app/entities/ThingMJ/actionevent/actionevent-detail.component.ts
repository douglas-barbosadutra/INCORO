import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActionevent } from 'app/shared/model/ThingMJ/actionevent.model';

@Component({
    selector: 'jhi-actionevent-detail',
    templateUrl: './actionevent-detail.component.html'
})
export class ActioneventDetailComponent implements OnInit {
    actionevent: IActionevent;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ actionevent }) => {
            this.actionevent = actionevent;
        });
    }

    previousState() {
        window.history.back();
    }
}
