import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegfinComponent } from './regfin.component';

describe('RegfinComponent', () => {
  let component: RegfinComponent;
  let fixture: ComponentFixture<RegfinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegfinComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegfinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
