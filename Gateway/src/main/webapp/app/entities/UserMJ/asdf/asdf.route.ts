import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Asdf } from 'app/shared/model/UserMJ/asdf.model';
import { AsdfService } from './asdf.service';
import { AsdfComponent } from './asdf.component';
import { AsdfDetailComponent } from './asdf-detail.component';
import { AsdfUpdateComponent } from './asdf-update.component';
import { AsdfDeletePopupComponent } from './asdf-delete-dialog.component';
import { IAsdf } from 'app/shared/model/UserMJ/asdf.model';

@Injectable({ providedIn: 'root' })
export class AsdfResolve implements Resolve<IAsdf> {
    constructor(private service: AsdfService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((asdf: HttpResponse<Asdf>) => asdf.body));
        }
        return of(new Asdf());
    }
}

export const asdfRoute: Routes = [
    {
        path: 'asdf',
        component: AsdfComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Asdfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asdf/:id/view',
        component: AsdfDetailComponent,
        resolve: {
            asdf: AsdfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Asdfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asdf/new',
        component: AsdfUpdateComponent,
        resolve: {
            asdf: AsdfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Asdfs'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'asdf/:id/edit',
        component: AsdfUpdateComponent,
        resolve: {
            asdf: AsdfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Asdfs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const asdfPopupRoute: Routes = [
    {
        path: 'asdf/:id/delete',
        component: AsdfDeletePopupComponent,
        resolve: {
            asdf: AsdfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Asdfs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
