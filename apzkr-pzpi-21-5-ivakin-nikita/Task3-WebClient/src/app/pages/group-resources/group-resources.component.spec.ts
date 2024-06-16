import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupResourcesComponent } from './group-resources.component';

describe('GroupResourcesComponent', () => {
  let component: GroupResourcesComponent;
  let fixture: ComponentFixture<GroupResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GroupResourcesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroupResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
