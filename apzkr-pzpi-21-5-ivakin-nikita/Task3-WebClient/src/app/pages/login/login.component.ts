import { Component } from '@angular/core';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services/authentication-controller.service';
import { CustomErrorResponse } from '../../services/models/custom-error-repsonse';
import { HttpErrorResponse } from '@angular/common/http';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {


  authRequest: AuthenticationRequest = {email: "", password: ""};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService
  ) {}

  onSubmit() {
    this.errorMsg = [];

    console.log("email: " + this.authRequest.email);
    console.log("password: " + this.authRequest.password);

    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse) => {
        console.log(res);
        if (res && res.access_token) {
          this.tokenService.token = res.access_token;
          this.router.navigate(['/']);
        } else {
          console.error('Invalid response format:', res);
          this.errorMsg = ["Invalid server response. Please try again later."];
        }
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
    console.log('Login cancelled');
    this.authRequest.email = '';
    this.authRequest.password = '';
    this.router.navigate(['']);
  }
}
