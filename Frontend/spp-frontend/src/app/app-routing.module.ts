import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import {HomePageComponent} from './home-page/home-page.component';
import {AccountsComponent} from './accounts/accounts.component';
import {CreditsComponent} from './credits/credits.component';
import {InvestmentsComponent} from './investments/investments.component';
import {LogComponent} from "./log/log.component";

const routes: Routes = [
  { path: '', redirectTo: 'log', pathMatch: 'full' },
  { path: 'home', component: HomePageComponent },
  { path: 'log', component: LogComponent },
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
