import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import{RegComponent} from '../app/reg/reg.component'
import{LogComponent} from '../app/log/log.component'

const routes: Routes = [
  {path:'',redirectTo:'log',pathMatch:'full'},
  {path:'reg', component:RegComponent},
  {path:'log', component:LogComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
