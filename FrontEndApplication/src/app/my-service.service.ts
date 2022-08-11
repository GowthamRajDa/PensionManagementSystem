import { Injectable } from '@angular/core';
import { aadharDetails } from './Models/aadharDetails.model';
import { jwtToken } from './Models/jwt.model';

@Injectable({
  providedIn: 'root'
})
export class MyServiceService {

  private jwtToken?:jwtToken;
  public aadharDetails?:aadharDetails;

  constructor() { }

  updateAadharDetails(aadhar:aadharDetails){
    this.aadharDetails=aadhar;
  }
}




