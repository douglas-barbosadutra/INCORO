import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Thing } from 'app/shared/model/ThingMJ/thing.model';
import { ThingService } from './thing.service';
import { ThingComponent } from './thing.component';
import { ThingDetailComponent } from './thing-detail.component';
import { ThingUpdateComponent } from './thing-update.component';
import { ThingDeletePopupComponent } from './thing-delete-dialog.component';
import { IThing } from 'app/shared/model/ThingMJ/thing.model';

@Injectable({ providedIn: 'root' })
export class ThingResolve implements Resolve<IThing> {
    constructor(private service: ThingService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((thing: HttpResponse<Thing>) => thing.body));
        }
        return of(new Thing());
    }
}

export const thingRoute: Routes = [
    {
        path: 'thing',
        component: ThingComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Things'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thing/:id/view',
        component: ThingDetailComponent,
        resolve: {
            thing: ThingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Things'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thing/new',
        component: ThingUpdateComponent,
        resolve: {
            thing: ThingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Things'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'thing/:id/edit',
        component: ThingUpdateComponent,
        resolve: {
            thing: ThingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Things'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const thingPopupRoute: Routes = [
    {
        path: 'thing/:id/delete',
        component: ThingDeletePopupComponent,
        resolve: {
            thing: ThingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Things'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
