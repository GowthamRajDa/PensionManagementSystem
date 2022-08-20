import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { ErrorObserver, Observable, observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs';
import { aadharDetails } from '../Models/aadharDetails.model';
import { pensionDetails } from '../Models/pensionDetails.model';
import { MyServiceService } from '../my-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aadhar-detial-page',
  templateUrl: './aadhar-detial-page.component.html',
  styleUrls: ['./aadhar-detial-page.component.css'],
})
export class AadharDetialPageComponent implements OnInit {
  aadharDetails?: aadharDetails;
  pensionDetails?: pensionDetails;

  constructor(
    private http: HttpClient,
    private service: MyServiceService,
    private router: Router
  ) {}


  logout(){
    this.aadharDetails=undefined;
    this.pensionDetails=undefined;
    localStorage.setItem('jwtToken','');
    this.router.navigate(['Homepage']);
  }

  ProcessPension() {
    this.http
      .post<pensionDetails>(
        this.service.ipaddress+':8082/ProcessPension/' +
          this.aadharDetails?.aadharNumber,
        '',
        {
          headers: new HttpHeaders({
            Authorization: 'Bearer ' + localStorage.getItem('jwtToken'),
          }),
        }
      )
      .pipe(
        map((response: pensionDetails) => {
          return response;
        })
      )
      .subscribe((responseData: pensionDetails) => {
        console.log(responseData);
        this.pensionDetails = responseData;
      });
  }

  ngOnInit(): void {
    if (localStorage.getItem('jwtToken') == '' || localStorage.getItem('jwtToken')==null ) {
      this.router.navigate(['login']);
    }

    if(this.service.aadharDetails==null){
      this.router.navigate(['Homepage']);
    }
    this.aadharDetails = this.service.aadharDetails;
  }
}
