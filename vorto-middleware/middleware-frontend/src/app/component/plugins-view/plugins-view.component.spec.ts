import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PluginsViewComponent } from './plugins-view.component';

describe('PluginsViewComponent', () => {
  let component: PluginsViewComponent;
  let fixture: ComponentFixture<PluginsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PluginsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PluginsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
