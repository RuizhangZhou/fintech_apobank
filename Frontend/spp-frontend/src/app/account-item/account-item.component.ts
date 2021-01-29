import {Component, Input, OnInit} from '@angular/core';

import {Account} from '../rest.service';
import {NavigationComponent} from "../navigation/navigation.component";

@Component({
  selector: 'app-account-item',
  templateUrl: './account-item.component.html',
  styleUrls: ['./account-item.component.css']
})
export class AccountItemComponent implements OnInit {

  @Input() accountInput!: Account;

  account: Account = <Account> {};

  constructor() { }

  ngOnInit(): void {
    this.account = this.accountInput;
  }

}
