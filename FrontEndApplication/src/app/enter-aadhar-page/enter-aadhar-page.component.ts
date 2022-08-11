import { Component, Input, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { aadharDetails } from '../Models/aadharDetails.model';
import { map } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { MyServiceService } from '../my-service.service';

@Component({
  selector: 'app-enter-aadhar-page',
  templateUrl: './enter-aadhar-page.component.html',
  styleUrls: ['./enter-aadhar-page.component.css'],
})
export class EnterAadharPageComponent implements OnInit {
  public aadharDetails?: aadharDetails;
  public invalidDetails = false;
  public errorMessage?: String;

  constructor(
    private http: HttpClient,
    private router: Router,
    private service: MyServiceService
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem('jwtToken') == '' || localStorage.getItem('jwtToken')==null ) {
      this.router.navigate(['login']);
    }
  }

  GetAadharDetails(GetData: any) {
    let aadhar: String = GetData['AadharNumber'].toString();
    if (aadhar.length == 12) {
      this.http
        .get<aadharDetails>(
          'http://localhost:8081/PensionerDetailByAadhaar/' +
            GetData['AadharNumber'],
          {
            headers: new HttpHeaders({
              Authorization: 'Bearer ' + localStorage.getItem('jwtToken'),
            }),
          }
        )
        .pipe(
          map((responsedata: aadharDetails) => {
            return responsedata;
          })
        )
        .subscribe({
          next: (ResponseData) => {
            this.aadharDetails = ResponseData;
            console.log(this.aadharDetails);
            this.service.updateAadharDetails(this.aadharDetails);
            this.router.navigate(['member-details']);
          },
          error: (error) => {
            let errorcode: number = error.status;
            if (errorcode == 404) {
              this.invalidDetails = true;
              this.errorMessage =
                'Entered details not found on our database. Please try again!';
            } else if (errorcode == 401) {
              this.invalidDetails = true;
              this.errorMessage = 'Unauthorized Request.Please Login!';
              this.router.navigate(['login']);
            } else {
              this.invalidDetails = true;
              this.errorMessage =
                'Internal Server error(' +
                errorcode +
                '). Please try again later.';
            }
          },
        });
    } else {
      this.invalidDetails = true;
      this.errorMessage = 'Please enter a Valid AadharNumber(12)';
    }
  }
}
