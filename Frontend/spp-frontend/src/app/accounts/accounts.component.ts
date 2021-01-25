import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from '../Models/Navigation';
import {RestService, Account} from "../rest.service";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {

  accounts: Account[] = <Account[]> {};

  constructor(public rest: RestService) { }

  ngOnInit(): void {
    this.getAccounts(this.rest.test_customer_number);
  }

  getAccounts(customer_number: string): void {
    this.rest.getAccountsByCustomer(customer_number).subscribe((resp: any) => {
      this.accounts = resp;
      console.log(this.accounts);
    });
  }
}
