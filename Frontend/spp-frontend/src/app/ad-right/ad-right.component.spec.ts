import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdRightComponent } from './ad-right.component';

describe('AdRightComponent', () => {
  let component: AdRightComponent;
  let fixture: ComponentFixture<AdRightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdRightComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdRightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
