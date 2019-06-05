import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAsdf } from 'app/shared/model/UserMJ/asdf.model';

@Component({
    selector: 'jhi-asdf-detail',
    templateUrl: './asdf-detail.component.html'
})
export class AsdfDetailComponent implements OnInit {
    asdf: IAsdf;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ asdf }) => {
            this.asdf = asdf;
        });
    }

    previousState() {
        window.history.back();
    }
}
