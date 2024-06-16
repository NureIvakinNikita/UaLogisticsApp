/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { CarCheckDto } from '../models/car-check-dto';
import { confirmDeliveryOfRequest } from '../fn/logistic-commander-controller/confirm-delivery-of-request';
import { ConfirmDeliveryOfRequest$Params } from '../fn/logistic-commander-controller/confirm-delivery-of-request';
import { createSupplyCar } from '../fn/logistic-commander-controller/create-supply-car';
import { CreateSupplyCar$Params } from '../fn/logistic-commander-controller/create-supply-car';
import { getAllRequests } from '../fn/logistic-commander-controller/get-all-requests';
import { GetAllRequests$Params } from '../fn/logistic-commander-controller/get-all-requests';
import { getCarChecks } from '../fn/logistic-commander-controller/get-car-checks';
import { GetCarChecks$Params } from '../fn/logistic-commander-controller/get-car-checks';
import { SupplyRequest } from '../models/supply-request';
import { takeExecutionOfRequest } from '../fn/logistic-commander-controller/take-execution-of-request';
import { TakeExecutionOfRequest$Params } from '../fn/logistic-commander-controller/take-execution-of-request';

@Injectable({ providedIn: 'root' })
export class LogisticCommanderControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly ConfirmDeliveryOfRequestPath = '/api/log-com/confirm/delivery-of-supply-request/{id}';

  confirmDeliveryOfRequest$Response(params: ConfirmDeliveryOfRequest$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return confirmDeliveryOfRequest(this.http, this.rootUrl, params, context);
  }

  confirmDeliveryOfRequest(params: ConfirmDeliveryOfRequest$Params, context?: HttpContext): Observable<boolean> {
    return this.confirmDeliveryOfRequest$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly TakeExecutionOfRequestPath = '/api/log-com/take/execution-of-supply-request/{id}';

  takeExecutionOfRequest$Response(params: TakeExecutionOfRequest$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return takeExecutionOfRequest(this.http, this.rootUrl, params, context);
  }

  takeExecutionOfRequest(params: TakeExecutionOfRequest$Params, context?: HttpContext): Observable<boolean> {
    return this.takeExecutionOfRequest$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly CreateSupplyCarPath = '/api/log-com/create/supply-car';

  createSupplyCar$Response(params: CreateSupplyCar$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createSupplyCar(this.http, this.rootUrl, params, context);
  }

  createSupplyCar(params: CreateSupplyCar$Params, context?: HttpContext): Observable<boolean> {
    return this.createSupplyCar$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly GetCarChecksPath = '/api/log-com/get/car-checks/{id}';

  getCarChecks$Response(params: GetCarChecks$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CarCheckDto>>> {
    return getCarChecks(this.http, this.rootUrl, params, context);
  }

  getCarChecks(params: GetCarChecks$Params, context?: HttpContext): Observable<Array<CarCheckDto>> {
    return this.getCarChecks$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CarCheckDto>>): Array<CarCheckDto> => r.body)
    );
  }

  static readonly GetAllRequestsPath = '/api/log-com/get/all-requests';

  getAllRequests$Response(params?: GetAllRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getAllRequests(this.http, this.rootUrl, params, context);
  }

  getAllRequests(params?: GetAllRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getAllRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

}
