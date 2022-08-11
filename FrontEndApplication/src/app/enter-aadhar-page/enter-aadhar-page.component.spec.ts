import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterAadharPageComponent } from './enter-aadhar-page.component';

describe('EnterAadharPageComponent', () => {
  let component: EnterAadharPageComponent;
  let fixture: ComponentFixture<EnterAadharPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnterAadharPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterAadharPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
