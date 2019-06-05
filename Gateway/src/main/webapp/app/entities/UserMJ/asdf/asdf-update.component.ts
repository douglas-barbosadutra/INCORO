import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAsdf } from 'app/shared/model/UserMJ/asdf.model';
import { AsdfService } from './asdf.service';

@Component({
    selector: 'jhi-asdf-update',
    templateUrl: './asdf-update.component.html'
})
export class AsdfUpdateComponent implements OnInit {
    private _asdf: IAsdf;
    isSaving: boolean;

    constructor(private asdfService: AsdfService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ asdf }) => {
            this.asdf = asdf;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.asdf.id !== undefined) {
            this.subscribeToSaveResponse(this.asdfService.update(this.asdf));
        } else {
            this.subscribeToSaveResponse(this.asdfService.create(this.asdf));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAsdf>>) {
        result.subscribe((res: HttpResponse<IAsdf>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get asdf() {
        return this._asdf;
    }

    set asdf(asdf: IAsdf) {
        this._asdf = asdf;
    }
}
