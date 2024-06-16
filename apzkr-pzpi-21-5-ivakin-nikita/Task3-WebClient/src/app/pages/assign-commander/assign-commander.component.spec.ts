import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignCommanderComponent } from './assign-commander.component';

describe('AssignCommanderComponent', () => {
  let component: AssignCommanderComponent;
  let fixture: ComponentFixture<AssignCommanderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignCommanderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignCommanderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
