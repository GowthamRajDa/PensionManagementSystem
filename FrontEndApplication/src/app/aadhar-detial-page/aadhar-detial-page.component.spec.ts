import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AadharDetialPageComponent } from './aadhar-detial-page.component';

describe('AadharDetialPageComponent', () => {
  let component: AadharDetialPageComponent;
  let fixture: ComponentFixture<AadharDetialPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AadharDetialPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AadharDetialPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
