import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerInfoPersonalComponent } from './customer-info-personal.component';

describe('CustomerInfoPersonalComponent', () => {
  let component: CustomerInfoPersonalComponent;
  let fixture: ComponentFixture<CustomerInfoPersonalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerInfoPersonalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerInfoPersonalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
