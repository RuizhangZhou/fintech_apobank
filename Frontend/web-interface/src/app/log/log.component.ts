import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import{Router} from '@angular/router'

import{MatInputModule} from '@angular/material/input'
import{MatSelectModule} from '@angular/material/select'
import{MatButtonModule} from '@angular/material/button'
import{MatCheckboxModule} from '@angular/material/checkbox'
import{MatChipsModule} from '@angular/material/chips'
import{RegComponent} from '../reg/reg.component';




@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.scss']
  
})
export class LogComponent implements OnInit {
  hide = true;
  formm!: FormGroup;
  formm1!:FormGroup;
constructor(private fb: FormBuilder,private router:Router) { }

goToPage(reg:string):void{
  this.router.navigate([`${reg}`]);
}
goToPage2(home:string):void{
  this.router.navigate([`${home}`]);
}

   
ngOnInit() {
  this.formm = this.fb.group({
    Benutzername: '',
    
  })
  this.formm1 = this.fb.group({
    
    Passwort: ''
  })
  this.formm.valueChanges.subscribe(console.log);
  this.formm1.valueChanges.subscribe(console.log);

 


}



}

