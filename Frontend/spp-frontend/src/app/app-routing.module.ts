import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import {HomePageComponent} from './home-page/home-page.component';
import {AccountsComponent} from './accounts/accounts.component';
import {CreditsComponent} from './credits/credits.component';
import {InvestmentsComponent} from './investments/investments.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'customer-info', component: CustomerInfoComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'credits', component: CreditsComponent },
  { path: 'investments', component: InvestmentsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
