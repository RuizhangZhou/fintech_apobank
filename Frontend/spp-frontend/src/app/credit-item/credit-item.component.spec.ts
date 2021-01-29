import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreditItemComponent } from './credit-item.component';

describe('CreditItemComponent', () => {
  let component: CreditItemComponent;
  let fixture: ComponentFixture<CreditItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreditItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreditItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
