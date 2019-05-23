import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoDashboardComponent } from './bo-dashboard.component';

describe('BoDashboardComponent', () => {
  let component: BoDashboardComponent;
  let fixture: ComponentFixture<BoDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
