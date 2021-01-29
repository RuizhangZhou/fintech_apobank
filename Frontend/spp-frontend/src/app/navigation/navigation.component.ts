import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from '../Models/Navigation';
import {AppRoutingModule} from '../app-routing.module';
import {Router} from '@angular/router';
import {NavItem} from '../Models/NavItem';
import { Location } from '@angular/common';
import {CustomerInfoComponent} from '../customer-info/customer-info.component';
import {MatDialog, MatDialogConfig, MatDialogRef} from '@angular/material/dialog';
import {CustomerInfoPersonalComponent} from '../customer-info-personal/customer-info-personal.component';
import {RestService, Customer} from '../rest.service';
@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  customer: Customer = <Customer> {};
  pCustomer: Customer = <Customer> {};
  constructor(private router: Router, private location: Location, public rest: RestService,private dialog: MatDialog/*, public dialogRef: MatDialogRef<NavigationComponent>*/) {}
  navigation = NAVIGATION;
  public currentSelected = '';

  ngOnInit(): void {
    this.getCustomer(this.rest.test_customer_number);
    console.log(this.location.path());
    switch (this.location.path()) {
      case '/home':
        this.currentSelected = 'Übersicht';
        break;
      case '/customer-info':
        this.currentSelected = 'Meine Daten';
        break;
      case '/accounts':
        this.currentSelected = 'Konten';
        break;
      case '/credits':
        this.currentSelected = 'Kredite';
        break;
      case '/investments':
        this.currentSelected = 'Geldanlagen';
        break;
    }
  }
  onSelect1(customer: Customer): void{
    //this.selectedCustomer = this.customer;
  }

  getCustomer(customer_number: string): void {
    this.rest.getCustomer(customer_number).subscribe((resp: any) => {
      this.customer = resp;
      console.log(this.customer);
      
    });
  }
  onSelect(page: NavItem): void{
    this.currentSelected = page.name;
    this.router.navigateByUrl(page.path);
  }

  onLogin(log:string): void{
    this.router.navigate([`${log}`]);
   ;
  }
}
