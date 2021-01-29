import {Component, Input, OnInit} from '@angular/core';

import {Investment} from '../rest.service';

@Component({
  selector: 'app-investment-item',
  templateUrl: './investment-item.component.html',
  styleUrls: ['./investment-item.component.css']
})
export class InvestmentItemComponent implements OnInit {

  @Input() investmentInput!: Investment;

  investment: Investment = <Investment> {};

  constructor() { }

  ngOnInit(): void {
    this.investment = this.investmentInput;
  }

}
