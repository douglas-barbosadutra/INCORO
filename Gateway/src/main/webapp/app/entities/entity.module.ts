import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewayUtenteModule as UserMjUtenteModule } from './UserMJ/utente/utente.module';
import { GatewayAsdfModule as UserMjAsdfModule } from './UserMJ/asdf/asdf.module';
import { GatewayThingModule as ThingMjThingModule } from './ThingMJ/thing/thing.module';
import { GatewayActioneventModule as ThingMjActioneventModule } from './ThingMJ/actionevent/actionevent.module';
import { GatewayLoginModule as UserMjLoginModule } from './UserMJ/login/login.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        UserMjUtenteModule,
        UserMjAsdfModule,
        ThingMjThingModule,
        ThingMjActioneventModule,
        
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
