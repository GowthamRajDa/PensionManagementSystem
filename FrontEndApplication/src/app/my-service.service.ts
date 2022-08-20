import { Injectable } from '@angular/core';
import { aadharDetails } from './Models/aadharDetails.model';
import { jwtToken } from './Models/jwt.model';

@Injectable({
  providedIn: 'root'
})
export class MyServiceService {

  private jwtToken?:jwtToken;
  public aadharDetails?:aadharDetails;

  //Enter the Public IP below

  //public ipaddress:String="http://localhost";
  public ipaddress:String="http://13.233.39.126";


  constructor() { }

  updateAadharDetails(aadhar:aadharDetails){
    this.aadharDetails=aadhar;
  }
}




