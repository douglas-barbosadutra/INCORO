/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { UtenteDetailComponent } from 'app/entities/UserMJ/utente/utente-detail.component';
import { Utente } from 'app/shared/model/UserMJ/utente.model';

describe('Component Tests', () => {
    describe('Utente Management Detail Component', () => {
        let comp: UtenteDetailComponent;
        let fixture: ComponentFixture<UtenteDetailComponent>;
        const route = ({ data: of({ utente: new Utente('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [UtenteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtenteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtenteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utente).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
