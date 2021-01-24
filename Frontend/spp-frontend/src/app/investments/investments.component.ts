import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from "../Models/Navigation";

@Component({
  selector: 'app-investments',
  templateUrl: './investments.component.html',
  styleUrls: ['./investments.component.css']
})
export class InvestmentsComponent implements OnInit {
  investments = NAVIGATION;
  constructor() { }

  ngOnInit(): void {
  }

}
