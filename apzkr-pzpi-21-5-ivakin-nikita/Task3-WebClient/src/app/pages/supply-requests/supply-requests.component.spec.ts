import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplyRequestsComponent } from './supply-requests.component';

describe('SupplyRequestsComponent', () => {
  let component: SupplyRequestsComponent;
  let fixture: ComponentFixture<SupplyRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SupplyRequestsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SupplyRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
