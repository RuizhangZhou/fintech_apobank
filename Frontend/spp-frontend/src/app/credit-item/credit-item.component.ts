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
  pLoan: Loan = <Loan> {};

  constructor() { }

  ngOnInit(): void {
    this.loan = this.loanInput;
    this.prettifyLoans();
  }

  prettifyLoans(): void {
    switch(this.loan.status){
      case "TIMELY": {
        this.pLoan.status="Pünktlich";
        break;
      }
      case "GRACE_PERIOD": {
        this.pLoan.status="Leihfrist";
        break;
      }
      case "DEFAULT": {
        this.pLoan.status="Kreditausfall";
        break;
      }
      case "DEFICIT": {
        this.pLoan.status="Defizit";
        break;
      }
      case "IRRECOVERABLE_DEBT": {
        this.pLoan.status="Uneinbringliche Schulden";
        break;
      }case "CLOSED": {
        this.pLoan.status="Geschlossen";
        break;
      }
    }
    switch (this.loan.type){
      case "carLoan": {
        this.pLoan.type="Autokredit";
        break;
      }
      case "constructionLoan": {
        this.pLoan.type="Baukredit";
        break;
      }
      case "mortgage": {
        this.pLoan.type="Hypothek";
        break;
      }
      case "privateLoan": {
        this.pLoan.type="Privatkredit";
        break;
      }
      case "propertyLoan": {
        this.pLoan.type="Immobilienkredit";
        break;
      }
    }

  }

}
