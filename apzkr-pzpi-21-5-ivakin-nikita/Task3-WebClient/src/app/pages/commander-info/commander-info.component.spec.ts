import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommanderInfoComponent } from './commander-info.component';

describe('CommanderInfoComponent', () => {
  let component: CommanderInfoComponent;
  let fixture: ComponentFixture<CommanderInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CommanderInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommanderInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
