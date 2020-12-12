import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ads',
  templateUrl: './ads.component.html',
  styleUrls: ['./ads.component.scss']
})
export class AdsComponent implements OnInit {

  public health:boolean = false;//=needHealth();call the method to get the result from decison of machine learning model
  public digital:boolean = false;//=needDigital();

  constructor() { }

  ngOnInit(): void {
  }

}
