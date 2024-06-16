/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { scanCar } from '../fn/scanning-device-controller/scan-car';
import { ScanCar$Params } from '../fn/scanning-device-controller/scan-car';

@Injectable({ providedIn: 'root' })
export class ScanningDeviceControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly ScanCarPath = '/api/device/scan-car';

  scanCar$Response(params: ScanCar$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return scanCar(this.http, this.rootUrl, params, context);
  }

 scanCar(params: ScanCar$Params, context?: HttpContext): Observable<string> {
    return this.scanCar$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
