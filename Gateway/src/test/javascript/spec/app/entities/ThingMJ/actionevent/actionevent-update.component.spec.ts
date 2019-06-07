/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { ActioneventUpdateComponent } from 'app/entities/ThingMJ/actionevent/actionevent-update.component';
import { ActioneventService } from 'app/entities/ThingMJ/actionevent/actionevent.service';
import { Actionevent } from 'app/shared/model/ThingMJ/actionevent.model';

describe('Component Tests', () => {
    describe('Actionevent Management Update Component', () => {
        let comp: ActioneventUpdateComponent;
        let fixture: ComponentFixture<ActioneventUpdateComponent>;
        let service: ActioneventService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GatewayTestModule],
                declarations: [ActioneventUpdateComponent]
            })
                .overrideTemplate(ActioneventUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ActioneventUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActioneventService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Actionevent('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.actionevent = entity;
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
                    const entity = new Actionevent();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.actionevent = entity;
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
