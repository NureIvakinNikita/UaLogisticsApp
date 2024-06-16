/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { authenticate } from '../fn/authentication-controller/authenticate';
import { Authenticate$Params } from '../fn/authentication-controller/authenticate';
import { AuthenticationResponse } from '../models/authentication-response';
import { refreshToken } from '../fn/authentication-controller/refresh-token';
import { RefreshToken$Params } from '../fn/authentication-controller/refresh-token';
import { register } from '../fn/authentication-controller/register';
import { Register$Params } from '../fn/authentication-controller/register';

@Injectable({ providedIn: 'root' })
export class AuthenticationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly RegisterPath = '/api/auth/register';

  
  register$Response(params: Register$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
    return register(this.http, this.rootUrl, params, context);
  }

  
  register(params: Register$Params, context?: HttpContext): Observable<AuthenticationResponse> {
    return this.register$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticationResponse>): AuthenticationResponse => r.body)
    );
  }

  static readonly RefreshTokenPath = '/api/auth/refresh-token';

  
  refreshToken$Response(params?: RefreshToken$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return refreshToken(this.http, this.rootUrl, params, context);
  }


  refreshToken(params?: RefreshToken$Params, context?: HttpContext): Observable<void> {
    return this.refreshToken$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }


  static readonly AuthenticatePath = '/api/auth/authenticate';

  
  authenticate$Response(params: Authenticate$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
    return authenticate(this.http, this.rootUrl, params, context);
  }

  
  authenticate(params: Authenticate$Params, context?: HttpContext): Observable<AuthenticationResponse> {
    return this.authenticate$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticationResponse>): AuthenticationResponse => r.body)
    );
  }

}
