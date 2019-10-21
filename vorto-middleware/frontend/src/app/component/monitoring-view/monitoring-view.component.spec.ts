import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoringViewComponent } from './monitoring-view.component';

describe('MonitoringViewComponent', () => {
  let component: MonitoringViewComponent;
  let fixture: ComponentFixture<MonitoringViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitoringViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitoringViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
