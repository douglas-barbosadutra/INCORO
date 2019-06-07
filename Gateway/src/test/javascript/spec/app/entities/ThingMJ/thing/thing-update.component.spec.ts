/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { ThingUpdateComponent } from 'app/entities/ThingMJ/thing/thing-update.component';
import { ThingService } from 'app/entities/ThingMJ/thing/thing.service';
import { Thing } from 'app/shared/model/ThingMJ/thing.model';

describe('Component Tests', () => {
    describe('Thing Management Update Component', () => {
        let comp: ThingUpdateComponent;
        let fixture: ComponentFixture<ThingUpdateComponent>;
        let service: ThingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [ThingUpdateComponent]
            })
                .overrideTemplate(ThingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ThingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ThingService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Thing('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.thing = entity;
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
                    const entity = new Thing();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.thing = entity;
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
