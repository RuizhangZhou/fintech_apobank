import { Component, OnInit } from '@angular/core';
import {NAVIGATION} from "../Models/Navigation";

@Component({
  selector: 'app-credits',
  templateUrl: './credits.component.html',
  styleUrls: ['./credits.component.css']
})
export class CreditsComponent implements OnInit {
  loans = NAVIGATION;
  constructor() { }

  ngOnInit(): void {
  }

}
