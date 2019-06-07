/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { ActioneventDetailComponent } from 'app/entities/ThingMJ/actionevent/actionevent-detail.component';
import { Actionevent } from 'app/shared/model/ThingMJ/actionevent.model';

describe('Component Tests', () => {
    describe('Actionevent Management Detail Component', () => {
        let comp: ActioneventDetailComponent;
        let fixture: ComponentFixture<ActioneventDetailComponent>;
        const route = ({ data: of({ actionevent: new Actionevent('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [ActioneventDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ActioneventDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActioneventDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.actionevent).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
