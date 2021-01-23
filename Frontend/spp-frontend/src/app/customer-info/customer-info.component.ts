import { Component, OnInit } from '@angular/core';
//import {Customer} from '../Models/Customer';
//import {CUSTOMERS} from '../Models/mock-customers';
import {NavigationComponent} from '../navigation/navigation.component';

import {RestService, Customer} from '../rest.service';

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrls: ['./customer-info.component.css'],
  providers: [NavigationComponent]
})
export class CustomerInfoComponent implements OnInit {
  customer: Customer = <Customer> {};
  //customers = CUSTOMERS;

  //selectedCustomer: Customer  = this.customer;

  constructor(private navigation: NavigationComponent,
              public rest: RestService) { }

  ngOnInit(): void {
    this.getCustomer(this.rest.test_customer_number);

    /*this.navigation.currentSelected = 'Meine Daten';*/
  }
  onSelect(customer: Customer): void{
    //this.selectedCustomer = this.customer;
  }

  getCustomer(customer_number: number): void {
    this.rest.getCustomer(customer_number).subscribe((resp: any) => {
      this.customer = resp;
      console.log(this.customer);
    });
  }
}
