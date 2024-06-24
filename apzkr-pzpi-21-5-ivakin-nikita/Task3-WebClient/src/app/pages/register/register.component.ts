import { Component, OnInit } from '@angular/core';
import { RegisterRequest } from '../../services/models';
import { Router } from '@angular/router';

import { HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService } from '../../services/services/authentication-controller.service';
import { TranslateService } from '@ngx-translate/core';

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
    private authService: AuthenticationService,
    private translate: TranslateService
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
                this.errorMsg = err.error.validationErrors.map(this.getErrorKey);
            } else {
                console.log(err);
                this.errorMsg = ['unexpectedError'];
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

  getErrorKey(error: string): string {
    switch (error) {
        case 'Password must contain at least one uppercase letter, one lowercase letter, and one digit':
            return 'passwordNotValid';
        case 'Password must be between 8 and 20 characters':
            return 'passwordNotValid2';
        case 'Password cannot be blank':
            return 'passwordNotValid3';
        case 'Last name must be between 2 and 30 characters':
            return 'lastNameNotValid2';
        case 'First name must be between 2 and 30 characters':
            return 'firstNameNotValid';
        case 'Second name must be between 2 and 30 characters':
            return 'secondNameNotValid';
        case 'Passport number must be exactly 9 digits':
            return 'passportNumberNotValid3';
        case 'Last name cannot be blank':
            return 'lastNameNotValid';
        case 'Passport number cannot be blank':
            return 'passportNumberNotValid';
        case 'Email cannot be blank':
            return 'emailNotValid2';
        case 'First name cannot be blank':
            return 'firstNameNotValid2';
        default:
            return '';
        }
    }
}