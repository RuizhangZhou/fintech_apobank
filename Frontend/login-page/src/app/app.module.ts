import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogComponent } from './log/log.component';





import { ReactiveFormsModule, FormsModule, FormGroup } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import{MatInputModule} from '@angular/material/input'
import{MatSelectModule} from '@angular/material/select'
import{MatButtonModule} from '@angular/material/button'
import{MatCheckboxModule} from '@angular/material/checkbox'
import{MatChipsModule} from '@angular/material/chips';
import { RegComponent } from './reg/reg.component'



@NgModule({
  declarations: [
    AppComponent,
    LogComponent,
    RegComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatChipsModule,
  


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
