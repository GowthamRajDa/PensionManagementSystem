import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { AadharDetialPageComponent } from './aadhar-detial-page/aadhar-detial-page.component';
import { EnterAadharPageComponent } from './enter-aadhar-page/enter-aadhar-page.component';
import { PensionDetailsComponent } from './pension-details/pension-details.component';
import {HttpClientModule} from '@angular/common/http';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    AadharDetialPageComponent,
    EnterAadharPageComponent,
    PensionDetailsComponent,
    PageNotFoundComponent,
  ],
  imports: [
    BrowserModule, 
    FormsModule,HttpClientModule,
    AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
