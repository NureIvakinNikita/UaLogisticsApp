import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendingResourcesComponent } from './sending-resources.component';

describe('SendingResourcesComponent', () => {
  let component: SendingResourcesComponent;
  let fixture: ComponentFixture<SendingResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SendingResourcesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SendingResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
