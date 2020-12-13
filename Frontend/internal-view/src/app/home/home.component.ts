import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public health:boolean = false;//=needHealth();call the method to get the result from decison of machine learning model
  public digital:boolean = false;//=needDigital();

  constructor() { }

  ngOnInit(): void {
  }

}
