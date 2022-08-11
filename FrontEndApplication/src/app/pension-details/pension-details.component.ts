import { Component, Input, OnInit } from '@angular/core';
import { pensionDetails } from '../Models/pensionDetails.model';

@Component({
  selector: 'app-pension-details',
  templateUrl: './pension-details.component.html',
  styleUrls: ['./pension-details.component.css']
})
export class PensionDetailsComponent implements OnInit {

  @Input()
  public pensionDetails?:pensionDetails;
  constructor() { }

  ngOnInit(): void {
    
  }

}
