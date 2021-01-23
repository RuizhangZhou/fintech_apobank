import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from '../Models/Navigation';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accounts = NAVIGATION;
  constructor() { }
  ngOnInit(): void {
  }

}
