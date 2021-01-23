import { Component, OnInit } from '@angular/core';
import {Customer} from '../Models/Customer';
import {CUSTOMERS} from '../Models/mock-customers';
import {NavigationComponent} from '../navigation/navigation.component';

@Component({
  selector: 'app-customer-info',
  templateUrl: './customer-info.component.html',
  styleUrls: ['./customer-info.component.css'],
  providers: [NavigationComponent]
})
export class CustomerInfoComponent implements OnInit {
  customer: Customer = {
    id: 1,
    first_name: 'Martin',
    last_name: 'Vichev'
  };
  customers = CUSTOMERS;

  selectedCustomer: Customer  = this.customer;

  constructor(private navigation: NavigationComponent) { }

  ngOnInit(): void {
    /*this.navigation.currentSelected = 'Meine Daten';*/
  }
  onSelect(customer: Customer): void{
    this.selectedCustomer = customer;
  }
}
