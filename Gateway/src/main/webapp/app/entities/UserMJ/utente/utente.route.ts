import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Utente } from 'app/shared/model/UserMJ/utente.model';
import { UtenteService } from './utente.service';
import { UtenteComponent } from './utente.component';
import { UtenteDetailComponent } from './utente-detail.component';
import { UtenteUpdateComponent } from './utente-update.component';
import { UtenteDeletePopupComponent } from './utente-delete-dialog.component';
import { IUtente } from 'app/shared/model/UserMJ/utente.model';

@Injectable({ providedIn: 'root' })
export class UtenteResolve implements Resolve<IUtente> {
    constructor(private service: UtenteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((utente: HttpResponse<Utente>) => utente.body));
        }
        return of(new Utente());
    }
}

export const utenteRoute: Routes = [
    {
        path: 'utente',
        component: UtenteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'Utentes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utente/:id/view',
        component: UtenteDetailComponent,
        resolve: {
            utente: UtenteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utentes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utente/new',
        component: UtenteUpdateComponent,
        resolve: {
            utente: UtenteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utentes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utente/:id/edit',
        component: UtenteUpdateComponent,
        resolve: {
            utente: UtenteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utentes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utentePopupRoute: Routes = [
    {
        path: 'utente/:id/delete',
        component: UtenteDeletePopupComponent,
        resolve: {
            utente: UtenteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Utentes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
