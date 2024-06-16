import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BattleGroupsListComponent } from './battle-groups-list.component';

describe('BattleGroupsListComponent', () => {
  let component: BattleGroupsListComponent;
  let fixture: ComponentFixture<BattleGroupsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BattleGroupsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BattleGroupsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
