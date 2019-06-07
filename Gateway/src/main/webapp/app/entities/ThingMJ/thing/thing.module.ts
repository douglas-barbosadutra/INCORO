import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared';
import {
    ThingComponent,
    ThingDetailComponent,
    ThingUpdateComponent,
    ThingDeletePopupComponent,
    ThingDeleteDialogComponent,
    thingRoute,
    thingPopupRoute
} from './';

const ENTITY_STATES = [...thingRoute, ...thingPopupRoute];

@NgModule({
    imports: [GatewaySharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ThingComponent, ThingDetailComponent, ThingUpdateComponent, ThingDeleteDialogComponent, ThingDeletePopupComponent],
    entryComponents: [ThingComponent, ThingUpdateComponent, ThingDeleteDialogComponent, ThingDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayThingModule {}
