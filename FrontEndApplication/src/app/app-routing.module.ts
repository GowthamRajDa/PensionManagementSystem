import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AadharDetialPageComponent } from './aadhar-detial-page/aadhar-detial-page.component';
import { EnterAadharPageComponent } from './enter-aadhar-page/enter-aadhar-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PensionDetailsComponent } from './pension-details/pension-details.component';

const routes: Routes = [
  {path:'',redirectTo:'/login',pathMatch:"full"},
  {path:'home',redirectTo:'/login',pathMatch:"full"},
  {path:'login',component:LoginPageComponent},
  {path:'Homepage',component:EnterAadharPageComponent},
  {path:'member-details',component:AadharDetialPageComponent},
  { path: '**', pathMatch: 'full', component: PageNotFoundComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
