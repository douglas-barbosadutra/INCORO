import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IThing } from 'app/shared/model/ThingMJ/thing.model';
import { ThingService } from './thing.service';

@Component({
    selector: 'jhi-thing-update',
    templateUrl: './thing-update.component.html'
})
export class ThingUpdateComponent implements OnInit {
    private _thing: IThing;
    isSaving: boolean;

    constructor(private thingService: ThingService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ thing }) => {
            this.thing = thing;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.thing.id !== undefined) {
            this.subscribeToSaveResponse(this.thingService.update(this.thing));
        } else {
            this.subscribeToSaveResponse(this.thingService.create(this.thing));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IThing>>) {
        result.subscribe((res: HttpResponse<IThing>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get thing() {
        return this._thing;
    }

    set thing(thing: IThing) {
        this._thing = thing;
    }
}
