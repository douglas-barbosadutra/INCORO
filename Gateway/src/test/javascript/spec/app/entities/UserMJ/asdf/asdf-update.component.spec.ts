/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { AsdfUpdateComponent } from 'app/entities/UserMJ/asdf/asdf-update.component';
import { AsdfService } from 'app/entities/UserMJ/asdf/asdf.service';
import { Asdf } from 'app/shared/model/UserMJ/asdf.model';

describe('Component Tests', () => {
    describe('Asdf Management Update Component', () => {
        let comp: AsdfUpdateComponent;
        let fixture: ComponentFixture<AsdfUpdateComponent>;
        let service: AsdfService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [AsdfUpdateComponent]
            })
                .overrideTemplate(AsdfUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AsdfUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AsdfService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Asdf('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.asdf = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Asdf();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.asdf = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
