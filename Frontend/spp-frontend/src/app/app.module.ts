import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import { FormsModule } from '@angular/forms';
import { NavigationComponent } from './navigation/navigation.component';
import { AdRightComponent } from './ad-right/ad-right.component';
import { FooterComponent } from './footer/footer.component';
import { HomePageComponent } from './home-page/home-page.component';
import { CreditsComponent } from './credits/credits.component';
import { AccountsComponent } from './accounts/accounts.component';
import { InvestmentsComponent } from './investments/investments.component';
import { CustomerInfoPersonalComponent } from './customer-info-personal/customer-info-personal.component'; // <-- NgModel lives here
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule, } from '@angular/material/dialog';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatRippleModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import { AccountItemComponent } from './account-item/account-item.component';
/*import { MatInputModule, MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';*/

@NgModule({
  declarations: [
    AppComponent,
    CustomerInfoComponent,
    NavigationComponent,
    AdRightComponent,
    FooterComponent,
    HomePageComponent,
    CreditsComponent,
    AccountsComponent,
    InvestmentsComponent,
    CustomerInfoPersonalComponent,
    AccountItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
  ],
  entryComponents: [
    CustomerInfoPersonalComponent
  ],
  exports: [
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule],
  providers: [ {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}}],
  bootstrap: [AppComponent]
})
export class AppModule { }
