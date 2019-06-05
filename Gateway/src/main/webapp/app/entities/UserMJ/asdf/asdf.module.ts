import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    AsdfComponent,
    AsdfDetailComponent,
    AsdfUpdateComponent,
    AsdfDeletePopupComponent,
    AsdfDeleteDialogComponent,
    asdfRoute,
    asdfPopupRoute
} from './';

const ENTITY_STATES = [...asdfRoute, ...asdfPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [AsdfComponent, AsdfDetailComponent, AsdfUpdateComponent, AsdfDeleteDialogComponent, AsdfDeletePopupComponent],
    entryComponents: [AsdfComponent, AsdfUpdateComponent, AsdfDeleteDialogComponent, AsdfDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayAsdfModule {}
