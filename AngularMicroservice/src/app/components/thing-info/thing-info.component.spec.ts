import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThingInfoComponent } from './thing-info.component';

describe('ThingInfoComponent', () => {
  let component: ThingInfoComponent;
  let fixture: ComponentFixture<ThingInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThingInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThingInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
