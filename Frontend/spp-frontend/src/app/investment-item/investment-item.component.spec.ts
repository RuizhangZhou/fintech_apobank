import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvestmentItemComponent } from './investment-item.component';

describe('InvestmentItemComponent', () => {
  let component: InvestmentItemComponent;
  let fixture: ComponentFixture<InvestmentItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvestmentItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvestmentItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
