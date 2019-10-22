import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoringConsole } from './monitoring-console.component';

describe('MonitoringConsole', () => {
  let component: MonitoringConsole;
  let fixture: ComponentFixture<MonitoringConsole>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitoringConsole ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitoringConsole);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
