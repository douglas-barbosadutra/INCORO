/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { AsdfDetailComponent } from 'app/entities/UserMJ/asdf/asdf-detail.component';
import { Asdf } from 'app/shared/model/UserMJ/asdf.model';

describe('Component Tests', () => {
    describe('Asdf Management Detail Component', () => {
        let comp: AsdfDetailComponent;
        let fixture: ComponentFixture<AsdfDetailComponent>;
        const route = ({ data: of({ asdf: new Asdf('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [AsdfDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AsdfDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AsdfDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.asdf).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
