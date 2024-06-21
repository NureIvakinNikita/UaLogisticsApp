import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarPopupComponent } from './car-popup.component';

describe('CarPopupComponent', () => {
  let component: CarPopupComponent;
  let fixture: ComponentFixture<CarPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarPopupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
