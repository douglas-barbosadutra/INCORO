import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Actionevent } from 'app/shared/model/ThingMJ/actionevent.model';
import { ActioneventService } from './actionevent.service';
import { ActioneventComponent } from './actionevent.component';
import { ActioneventDetailComponent } from './actionevent-detail.component';
import { ActioneventUpdateComponent } from './actionevent-update.component';
import { ActioneventDeletePopupComponent } from './actionevent-delete-dialog.component';
import { IActionevent } from 'app/shared/model/ThingMJ/actionevent.model';

@Injectable({ providedIn: 'root' })
export class ActioneventResolve implements Resolve<IActionevent> {
    constructor(private service: ActioneventService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((actionevent: HttpResponse<Actionevent>) => actionevent.body));
        }
        return of(new Actionevent());
    }
}

export const actioneventRoute: Routes = [
    {
        path: 'actionevent',
        component: ActioneventComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Actionevents'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'actionevent/:id/view',
        component: ActioneventDetailComponent,
        resolve: {
            actionevent: ActioneventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Actionevents'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'actionevent/new',
        component: ActioneventUpdateComponent,
        resolve: {
            actionevent: ActioneventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Actionevents'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'actionevent/:id/edit',
        component: ActioneventUpdateComponent,
        resolve: {
            actionevent: ActioneventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Actionevents'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const actioneventPopupRoute: Routes = [
    {
        path: 'actionevent/:id/delete',
        component: ActioneventDeletePopupComponent,
        resolve: {
            actionevent: ActioneventResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Actionevents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
