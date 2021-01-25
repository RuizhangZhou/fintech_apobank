import { Component, OnInit } from '@angular/core';
import {RestService, AdvertisementInfo} from '../rest.service';
import {isPackageNameSafeForAnalytics} from "@angular/cli/models/analytics";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  advertisementInfoAll: AdvertisementInfo[] = <AdvertisementInfo[]> {};
  advertisementInfo: AdvertisementInfo[] = <AdvertisementInfo[]> {};
  advertisementInfoFirst: AdvertisementInfo[] = <AdvertisementInfo[]> {};
  advertisementInfoLast: AdvertisementInfo[] = <AdvertisementInfo[]> {};

  constructor(public rest: RestService) { }

  ngOnInit(): void {
    this.getAdvertisementInfo(this.rest.test_customer_number);

   var items:any=document.getElementsByClassName('item');//图片
   var goPreBtn:any=document.getElementById('goPre');//
   var goNextBtn:any=document.getElementById('goNext');
   var points:any=document.getElementsByClassName('point');//点
   var closeBtn:any=document.getElementById('schliessen');
   var index:any=0;//index表示第几张图片在展示 ==> 第index张有active这个类名
   var time:any=0;//定时器图片跳转函数
    var n:number= this.advertisementInfo.length;

   var clearActive=function () {
     for(var i =0;i<items.length;i++){
       items[i].className='item';
     }
     for(var i =0;i<points.length;i++){
       points[i].className='point';
     }
   }

   var goIndex = function () {
     clearActive();
     points[index].className='point active';
     items[index].className='item active';
   }

   var goNext=function(){
     index=(index+1)%3; // TODO: replace with n
     goIndex();
     time=0;
   }

   var goPre=function(){
     index=(index+2)%3; // TODO: replace with n
     goIndex();
     time=0;
   }

   goNextBtn.addEventListener('click',function () {
     goNext();
   })

   goPreBtn.addEventListener('click',function () {
     goPre();
   })


     points[0].addEventListener('click',function () {
       index=0;
      //  var pointIndex=points[i].getAttribute('data-index');
      //  index=pointIndex;
       goIndex();
       time=0;
     })
     points[1].addEventListener('click',function () {
      index=1;
     //  var pointIndex=points[i].getAttribute('data-index');
     //  index=pointIndex;
      goIndex();
      time=0;
    })
    points[2].addEventListener('click',function () {
      index=2;
     //  var pointIndex=points[i].getAttribute('data-index');
     //  index=pointIndex;
      goIndex();
      time=0;
    })
    closeBtn.addEventListener('click',function(){
      var CarouselAD:any=document.getElementById('all');
      CarouselAD.style.display='none';
    })


   //5000ms 跳转一次
   setInterval(function (){
     time++;
     if(time == 50){
       goNext();
       time=0;
     }
   },100)

  }

  getAdvertisementInfo(customer_number: string): void {
    this.rest.getAdvertisementInfo(customer_number).subscribe((resp: any) => {
      this.advertisementInfoAll = resp;
      console.log(this.advertisementInfoAll);

      this.advertisementInfo = this.advertisementInfoAll.filter(
        info => info.shouldShow === true);
      console.log(this.advertisementInfo);

      this.advertisementInfoFirst = this.advertisementInfo.slice(0,1);
      console.log(this.advertisementInfoFirst);
      this.advertisementInfoLast = this.advertisementInfo.slice(1);
      console.log(this.advertisementInfoLast);
    });
  }

}
