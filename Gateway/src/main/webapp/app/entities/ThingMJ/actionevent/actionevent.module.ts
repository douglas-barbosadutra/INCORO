import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    ActioneventComponent,
    ActioneventDetailComponent,
    ActioneventUpdateComponent,
    ActioneventDeletePopupComponent,
    ActioneventDeleteDialogComponent,
    actioneventRoute,
    actioneventPopupRoute
} from './';

const ENTITY_STATES = [...actioneventRoute, ...actioneventPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ActioneventComponent,
        ActioneventDetailComponent,
        ActioneventUpdateComponent,
        ActioneventDeleteDialogComponent,
        ActioneventDeletePopupComponent
    ],
    entryComponents: [ActioneventComponent, ActioneventUpdateComponent, ActioneventDeleteDialogComponent, ActioneventDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayActioneventModule {}
