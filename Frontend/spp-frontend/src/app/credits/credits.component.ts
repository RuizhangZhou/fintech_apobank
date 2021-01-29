import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from "../Models/Navigation";
import {Loan, RestService} from "../rest.service";

@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent implements OnInit {

  loans: Loan[] = <Loan[]> {};

  constructor(public rest: RestService) { }

  ngOnInit(): void {
    this.getLoans(this.rest.test_customer_number);
  }

  getLoans(customer_number: string): void {
    this.rest.getCustomer(customer_number).subscribe((resp: any) => {
      this.loans = resp.loans;
      console.log(this.loans);
    });
  }

}
