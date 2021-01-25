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
  pCustomer: Customer = <Customer> {};

  //customers = CUSTOMERS;

  //selectedCustomer: Customer  = this.customer;

  constructor(private navigation: NavigationComponent,
              public rest: RestService) { }

  ngOnInit(): void {
    this.getCustomer(this.rest.test_customer_number);
    this.prettifyCustomer()
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

  prettifyCustomer(): void {
    switch(this.customer.job){
      case "ADMIN": {
        this.pCustomer.job = "Administrator";
        break;
      }
      case "UNKNOWN": {
        this.pCustomer.job = "Unbekannt";
        break;
      }
      case "UNEMPLOYED": {
        this.pCustomer.job = "Arbeitslos";
        break;
      }
      case "MANAGEMENT": {
        this.pCustomer.job = "Management";
        break;
      }
      case "HOUSEMAID": {
        this.pCustomer.job = "Hausmädchen";
        break;
      }
      case "ENTREPRENEUR": {
        this.pCustomer.job = "Unternehmer";
        break;
      }
      case "STUDENT": {
        this.pCustomer.job = "Student";
        break;
      }
      case "BLUE_COLLAR": {
        this.pCustomer.job = "Arbeiter";
        break;
      }
      case "SELF_EMPLOYED": {
        this.pCustomer.job = "Selbständig";
        break;
      }
      case "RETIRED": {
        this.pCustomer.job = "Ruhestand";
        break;
      }
      case "TECHNICIAN": {
        this.pCustomer.job = "Techniker";
        break;
      }
      case "SERVICES": {
        this.pCustomer.job = "Dienstleistung";
        break;
      }
    }
    switch(this.customer.relationshipStatus){
      case "MARRIED": {
        this.pCustomer.job = "Verheiratet";
        break;
      }
      case "REGISTERED_PARTNERSHIP": {
        this.pCustomer.job = "Registrierte Partnerschaft";
        break;
      }
      case "DIVORCED": {
        this.pCustomer.job = "Geschieden";
        break;
      }
      case "SINGLE": {
        this.pCustomer.job = "Single";
        break;
      }
      case "WIDOWED": {
        this.pCustomer.job = "Verwitwet";
        break;
      }
    }
    switch(this.customer.education){
      case "PRIMARY": {
        this.pCustomer.job = "Realschulabschluss";
        break;
      }
      case "SECONDARY": {
        this.pCustomer.job = "Abitur";
        break;
      }
      case "TERTIARY": {
        this.pCustomer.job = "Universitätsabschluss";
        break;
      }
      case "UNKNOWN": {
        this.pCustomer.job = "Unbekannt";
        break;
      }
    }
    this.pCustomer.birthday = this.customer.birthday.substring(8) + "." + this.customer.birthday.substring(5,7) + "." + this.customer.birthday.substring(0,4);


  }
}
