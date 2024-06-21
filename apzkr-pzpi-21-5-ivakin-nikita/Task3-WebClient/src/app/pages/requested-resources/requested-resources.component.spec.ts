import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestedResourcesComponent } from './requested-resources.component';

describe('RequestedResourcesComponent', () => {
  let component: RequestedResourcesComponent;
  let fixture: ComponentFixture<RequestedResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RequestedResourcesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RequestedResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
