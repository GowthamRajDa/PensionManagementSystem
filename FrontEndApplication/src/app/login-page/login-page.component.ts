import { HttpClient } from '@angular/common/http';
import { Component, OnInit, NgModule } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { jwtToken } from '../Models/jwt.model';
import { MyServiceService } from '../my-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  jwtToken?: jwtToken;

  invalidCred: Boolean = false;


  constructor(private http: HttpClient, private router: Router,private service: MyServiceService) {}

  Login(postData: { username: string; password: string }) {
   
      this.http
        .post<jwtToken>(this.service.ipaddress+':8080/authenticate', postData)
        .pipe(
          map((responsedata: jwtToken) => {
            return responsedata;
          })
        )
        .subscribe((responseData) => {
          if (responseData.jwtToken==="INVALID_CREDENTIALS") {
            this.invalidCred = true;
          } else {
            this.jwtToken = responseData;
            localStorage.setItem('jwtToken',<string>this.jwtToken.jwtToken);
            console.log(this.jwtToken);
            this.router.navigate(['Homepage']);
          }
        });
        
  }

  ngOnInit(): void {}
}
