import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerInfoComponent } from './customer-info/customer-info.component';
import {HomePageComponent} from './home-page/home-page.component';
import {AccountsComponent} from './accounts/accounts.component';
import {CreditsComponent} from './credits/credits.component';
import {InvestmentsComponent} from './investments/investments.component';
import {LogComponent} from "./log/log.component";
import {MainComponent} from "./main/main.component";

const routes: Routes = [
  { path: '', redirectTo: 'log', pathMatch: 'full'},
  { path: 'log', component: LogComponent},
  { path: 'main', component: MainComponent, children: [
      { path: '', redirectTo: 'home', pathMatch: 'full'},
      { path: 'home', component: HomePageComponent , outlet: 'mainRouter'},
      { path: 'customer-info', component: CustomerInfoComponent , outlet: 'mainRouter'},
      { path: 'accounts', component: AccountsComponent , outlet: 'mainRouter'},
      { path: 'credits', component: CreditsComponent , outlet: 'mainRouter'},
      { path: 'investments', component: InvestmentsComponent , outlet: 'mainRouter'}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
