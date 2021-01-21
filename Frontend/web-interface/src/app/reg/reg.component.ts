import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import{Router} from '@angular/router'

import{MatInputModule} from '@angular/material/input'
import{MatSelectModule} from '@angular/material/select'
import{MatButtonModule} from '@angular/material/button'
import{MatCheckboxModule} from '@angular/material/checkbox'
import{MatChipsModule} from '@angular/material/chips'
import{LogComponent} from'../log/log.component';
import { RegfinComponent } from '../regfin/regfin.component';

@Component({
  selector: 'app-reg',
  templateUrl: './reg.component.html',
  styleUrls: ['./reg.component.scss']
})
export class RegComponent implements OnInit {

  formm2!: FormGroup;
  formm3!:FormGroup;
constructor(private fb1: FormBuilder,private router:Router) { }

goToPage(regfin:string):void{
  this.router.navigate([`${regfin}`]);
}
   
ngOnInit() {
  this.formm3 = this.fb1.group({
    E1: '',
    
  })
  this.formm2 = this.fb1.group({
    
    E2: ''
  })
  this.formm3.valueChanges.subscribe(console.log);
  this.formm2.valueChanges.subscribe(console.log);


 


}

}
