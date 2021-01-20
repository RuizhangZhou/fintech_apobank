import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AccountsComponent } from './accounts/accounts.component';
import { CreditsComponent } from './credits/credits.component';
import { InvestmentsComponent } from './investments/investments.component';
import { LogoutComponent } from './logout/logout.component';
import { MyDataComponent } from './my-data/my-data.component';
import{RegComponent} from './reg/reg.component'
import{LogComponent} from './log/log.component'

const routes: Routes = [
  { path: '', redirectTo:'log',pathMatch:'full' },
  { path: 'reg', component: RegComponent },
  { path: 'log', component: LogComponent },
  { path: 'home', component: HomeComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'credits', component: CreditsComponent },
  { path: 'investments', component: InvestmentsComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'my-data', component: MyDataComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }