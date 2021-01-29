import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-customer-info-personal',
  templateUrl: './customer-info-personal.component.html',
  styleUrls: ['./customer-info-personal.component.css']
})
export class CustomerInfoPersonalComponent implements OnInit {
/*

  form: FormGroup;
  description:string;
*/

  constructor(
   /* private fb: FormBuilder,*/
    private dialogRef: MatDialogRef<CustomerInfoPersonalComponent>){}/*,
    @Inject(MAT_DIALOG_DATA) data) {

    /!*this.description = data.description;*!/

  }
*/
  ngOnInit(): void {/*
    this.form = fb.group({
      description: [description, []]
    });*/
  }

  save(): void {
    this.dialogRef.close(/*this.form.value*/);
  }

  close(): void {
    this.dialogRef.close();
  }
}
