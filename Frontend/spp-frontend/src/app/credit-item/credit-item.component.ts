import {Component, Input, OnInit} from '@angular/core';

import {Loan} from '../rest.service';

@Component({
  selector: 'app-credit-item',
  templateUrl: './credit-item.component.html',
  styleUrls: ['./credit-item.component.css']
})
export class CreditItemComponent implements OnInit {

  @Input() loanInput!: Loan;

  loan: Loan = <Loan> {};

  constructor() { }

  ngOnInit(): void {
    this.loan = this.loanInput;
  }

}
