import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from "../Models/Navigation";
import {Investment, RestService} from "../rest.service";

@Component({
  selector: 'app-investments',
  templateUrl: './investments.component.html',
  styleUrls: ['./investments.component.css']
})
export class InvestmentsComponent implements OnInit {
  investments: Investment[] = <Investment[]> {};
  constructor(public rest: RestService) { }

  ngOnInit(): void {
    this.getInvestments(this.rest.test_customer_number);
  }

  getInvestments(customer_number: string): void {
    this.rest.getCustomer(customer_number).subscribe((resp: any) => {
      this.investments = resp.investments;
      console.log(this.investments);
    });
  }

}
