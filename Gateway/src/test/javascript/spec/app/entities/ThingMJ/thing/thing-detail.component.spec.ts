/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { ThingDetailComponent } from 'app/entities/ThingMJ/thing/thing-detail.component';
import { Thing } from 'app/shared/model/ThingMJ/thing.model';

describe('Component Tests', () => {
    describe('Thing Management Detail Component', () => {
        let comp: ThingDetailComponent;
        let fixture: ComponentFixture<ThingDetailComponent>;
        const route = ({ data: of({ thing: new Thing('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [ThingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ThingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ThingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.thing).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
