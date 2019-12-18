import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MappingsViewComponent } from './mappings-view.component';

describe('MappingsViewComponent', () => {
  let component: MappingsViewComponent;
  let fixture: ComponentFixture<MappingsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MappingsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MappingsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
