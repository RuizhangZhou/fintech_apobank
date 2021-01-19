import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-data',
  templateUrl: './my-data.component.html',
  styleUrls: ['./my-data.component.css']
})
export class MyDataComponent implements OnInit {

  constructor() { }
  
  ngOnInit(): void {

    function showHide(id: string)
{
	var e = document.getElementById(id);

	if(e!.style.display == 'block')
		e!.style.display = 'none';
	else
		e!.style.display = 'block';
}

    let studentenwerbung : boolean = true;
    let Altwerbung : boolean = false;
if(studentenwerbung==true ){
  var img1=new Image();
  img1.src='V:/web-application/Frontend/internal-view/src/assets/images/gruen.png';
  var output1 = "true";
  document.getElementById("demo")!.innerHTML = output1;

}
else{
  var img1=new Image();
  img1.src='V:/web-application/Frontend/internal-view/src/assets/images/weiss.png';
  var output1 = "false";
  document.getElementById("demo")!.innerHTML = output1;
}

if(Altwerbung == false){
  var output2 = "false";
  document.getElementById("demo2")!.innerHTML = output2;
}
else{
  var output2 = "true";
  document.getElementById("demo2")!.innerHTML = output2;
}





    var tooltipSpan = document.getElementById('tooltip-span');
    window.onmousemove = function ( e: { clientX: any; clientY: any; }){
      var x = e.clientX,
          y = e.clientY;
      tooltipSpan!.style.top = (y + 20) + 'px';
      tooltipSpan!.style.left = (x + 20) + 'px';
    };
   
   
    
  };
  }
