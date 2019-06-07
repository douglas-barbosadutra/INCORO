import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IActionevent } from 'app/shared/model/ThingMJ/actionevent.model';
import { ActioneventService } from './actionevent.service';

@Component({
    selector: 'jhi-actionevent-update',
    templateUrl: './actionevent-update.component.html'
})
export class ActioneventUpdateComponent implements OnInit {
    private _actionevent: IActionevent;
    isSaving: boolean;

    constructor(private actioneventService: ActioneventService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ actionevent }) => {
            this.actionevent = actionevent;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.actionevent.id !== undefined) {
            this.subscribeToSaveResponse(this.actioneventService.update(this.actionevent));
        } else {
            this.subscribeToSaveResponse(this.actioneventService.create(this.actionevent));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IActionevent>>) {
        result.subscribe((res: HttpResponse<IActionevent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get actionevent() {
        return this._actionevent;
    }

    set actionevent(actionevent: IActionevent) {
        this._actionevent = actionevent;
    }
}
