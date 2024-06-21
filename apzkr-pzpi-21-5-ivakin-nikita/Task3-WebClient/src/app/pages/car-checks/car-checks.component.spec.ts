import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarChecksComponent } from './car-checks.component';

describe('CarChecksComponent', () => {
  let component: CarChecksComponent;
  let fixture: ComponentFixture<CarChecksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarChecksComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarChecksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
