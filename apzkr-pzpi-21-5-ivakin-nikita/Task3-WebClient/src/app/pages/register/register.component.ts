import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from '../../services/models';
import { Router } from '@angular/router';

import { HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService } from '../../services/services/authentication-controller.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerRequest: RegisterRequest = {
    firstName: '',
    lastName: '',
    secondName: '',
    passportNumber: '',
    email: '',
    password: '',
    post: 'BRIGADE_COMMANDER',
    rank: 'MAJOR_GENERAL', // Установите значение по умолчанию
    role: 'BRIGADE_COMMANDER' // Установите значение по умолчанию
  };
  ranks = [
    { value: 'MAJOR_GENERAL', label: 'Генерал майор' },
    { value: 'COLONEL', label: 'Полковник' },
    { value: 'MAJOR', label: 'Майор' },
    { value: 'LIEUTENANT_COLONEL', label: 'Підполковник' },
    { value: 'SENIOR_LIEUTENANT', label: 'Старший лейтенант' },
    { value: 'JUNIOR_LIEUTENANT', label: 'Молодший летенант' },
    { value: 'SCANNING_DEVICE', label: 'Скануючий дивайс' }
  ];
  roles = [
    'BRIGADE_COMMANDER',
    'BATTALION_COMMANDER',
    'COMPANY_COMMANDER',
    'PLAT_COMMANDER',
    'LOGISTIC_COMMANDER',
    'SCANNING_DEVICE',
    'ADMIN'
  ];
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  ngOnInit(): void {}

  onSubmit() {
    this.errorMsg = [];
    
    this.authService.register({ body: this.registerRequest }).subscribe({
      next: (res) => {
        this.router.navigate(['/login']);
      },
      error: (err: HttpErrorResponse) => {
        if (err.error) {
          this.errorMsg = err.error.validationErrors || [];
        } else {
          console.log(err);
          this.errorMsg = ["An unexpected error occurred. Please try again."];
        }
      }
    });
  }

  onCancel() {
    console.log('Registration cancelled');
    this.registerRequest.firstName = '';
    this.registerRequest.lastName = '';
    this.registerRequest.secondName = '';
    this.registerRequest.email = '';
    this.registerRequest.password = '';
    this.registerRequest.passportNumber = '';
    this.registerRequest.rank = 'MAJOR_GENERAL';
    this.registerRequest.role = 'BRIGADE_COMMANDER';
    this.router.navigate(['']);
  }
}