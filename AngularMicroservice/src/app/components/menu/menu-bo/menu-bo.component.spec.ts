import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuBoComponent } from './menu-bo.component';

describe('MenuBoComponent', () => {
  let component: MenuBoComponent;
  let fixture: ComponentFixture<MenuBoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuBoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuBoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
