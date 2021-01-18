import { Component, OnInit } from '@angular/core';
import { LogoutComponent } from '../logout/logout.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  

  public health:boolean = false;//=needHealth();call the method to get the result from decison of machine learning model
  public digital:boolean = true;//=needDigital();

  constructor() { }

  ngOnInit(): void {
  }

}
