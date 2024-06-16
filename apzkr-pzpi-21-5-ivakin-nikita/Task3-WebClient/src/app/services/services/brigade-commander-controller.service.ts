/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { askForResources2 } from '../fn/brigade-commander-controller/ask-for-resources-2';
import { AskForResources2$Params } from '../fn/brigade-commander-controller/ask-for-resources-2';
import { assignBattalionCommander } from '../fn/brigade-commander-controller/assign-battalion-commander';
import { AssignBattalionCommander$Params } from '../fn/brigade-commander-controller/assign-battalion-commander';
import { assignLogisticCommander } from '../fn/brigade-commander-controller/assign-logistic-commander';
import { AssignLogisticCommander$Params } from '../fn/brigade-commander-controller/assign-logistic-commander';
import { BattalionGroupDto } from '../models/battalion-group-dto';
import { BrigadeGroupDto } from '../models/brigade-group-dto';
import { confirmGettingOfResources2 } from '../fn/brigade-commander-controller/confirm-getting-of-resources-2';
import { ConfirmGettingOfResources2$Params } from '../fn/brigade-commander-controller/confirm-getting-of-resources-2';
import { createBattalion } from '../fn/brigade-commander-controller/create-battalion';
import { CreateBattalion$Params } from '../fn/brigade-commander-controller/create-battalion';
import { createBrigade } from '../fn/brigade-commander-controller/create-brigade';
import { CreateBrigade$Params } from '../fn/brigade-commander-controller/create-brigade';
import { createLogisticCompany } from '../fn/brigade-commander-controller/create-logistic-company';
import { CreateLogisticCompany$Params } from '../fn/brigade-commander-controller/create-logistic-company';
import { getBattalionGroups } from '../fn/brigade-commander-controller/get-battalion-groups';
import { GetBattalionGroups$Params } from '../fn/brigade-commander-controller/get-battalion-groups';
import { getBattalionsRequests } from '../fn/brigade-commander-controller/get-battalions-requests';
import { GetBattalionsRequests$Params } from '../fn/brigade-commander-controller/get-battalions-requests';
import { getBrigadeRequests } from '../fn/brigade-commander-controller/get-brigade-requests';
import { GetBrigadeRequests$Params } from '../fn/brigade-commander-controller/get-brigade-requests';
import { getBrigadeResources } from '../fn/brigade-commander-controller/get-brigade-resources';
import { GetBrigadeResources$Params } from '../fn/brigade-commander-controller/get-brigade-resources';
import { ResourcesUpdateResponse } from '../models/resources-update-response';
import { sendResources1 } from '../fn/brigade-commander-controller/send-resources-1';
import { SendResources1$Params } from '../fn/brigade-commander-controller/send-resources-1';
import { SupplyRequest } from '../models/supply-request';
import { updateBrigadeResources } from '../fn/brigade-commander-controller/update-brigade-resources';
import { UpdateBrigadeResources$Params } from '../fn/brigade-commander-controller/update-brigade-resources';

@Injectable({ providedIn: 'root' })
export class BrigadeCommanderControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly UpdateBrigadeResourcesPath = '/api/brig-com/update/brigade-resources';

 
  updateBrigadeResources$Response(params: UpdateBrigadeResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return updateBrigadeResources(this.http, this.rootUrl, params, context);
  }


  updateBrigadeResources(params: UpdateBrigadeResources$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.updateBrigadeResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  
  static readonly ConfirmGettingOfResources2Path = '/api/brig-com/confirm/getting-of-resources';


  confirmGettingOfResources2$Response(params: ConfirmGettingOfResources2$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return confirmGettingOfResources2(this.http, this.rootUrl, params, context);
  }


  confirmGettingOfResources2(params: ConfirmGettingOfResources2$Params, context?: HttpContext): Observable<boolean> {
    return this.confirmGettingOfResources2$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AssignLogisticCommanderPath = '/api/brig-com/assign/logistic-commander';

  
  assignLogisticCommander$Response(params: AssignLogisticCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return assignLogisticCommander(this.http, this.rootUrl, params, context);
  }

  
  assignLogisticCommander(params: AssignLogisticCommander$Params, context?: HttpContext): Observable<boolean> {
    return this.assignLogisticCommander$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  
  static readonly AssignBattalionCommanderPath = '/api/brig-com/assign/battalion-commander';

  
  assignBattalionCommander$Response(params: AssignBattalionCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return assignBattalionCommander(this.http, this.rootUrl, params, context);
  }


  assignBattalionCommander(params: AssignBattalionCommander$Params, context?: HttpContext): Observable<boolean> {
    return this.assignBattalionCommander$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly SendResources1Path = '/api/brig-com/send/resources-to-battalion';

 
  sendResources1$Response(params: SendResources1$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return sendResources1(this.http, this.rootUrl, params, context);
  }

  
  sendResources1(params: SendResources1$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.sendResources1$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly CreateLogisticCompanyPath = '/api/brig-com/create/logistic-company';

 
  createLogisticCompany$Response(params: CreateLogisticCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createLogisticCompany(this.http, this.rootUrl, params, context);
  }

  
  createLogisticCompany(params: CreateLogisticCompany$Params, context?: HttpContext): Observable<boolean> {
    return this.createLogisticCompany$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly CreateBrigadePath = '/api/brig-com/create/brigade';

  createBrigade$Response(params: CreateBrigade$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createBrigade(this.http, this.rootUrl, params, context);
  }

  createBrigade(params: CreateBrigade$Params, context?: HttpContext): Observable<boolean> {
    return this.createBrigade$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly CreateBattalionPath = '/api/brig-com/create/battalion';

  createBattalion$Response(params: CreateBattalion$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createBattalion(this.http, this.rootUrl, params, context);
  }

  createBattalion(params: CreateBattalion$Params, context?: HttpContext): Observable<boolean> {
    return this.createBattalion$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AskForResources2Path = '/api/brig-com/ask/for-resources';

 
  askForResources2$Response(params: AskForResources2$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return askForResources2(this.http, this.rootUrl, params, context);
  }

  
  askForResources2(params: AskForResources2$Params, context?: HttpContext): Observable<boolean> {
    return this.askForResources2$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  
  static readonly GetBrigadeRequestsPath = '/api/brig-com/get/brigade-requests';

  
  getBrigadeRequests$Response(params?: GetBrigadeRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getBrigadeRequests(this.http, this.rootUrl, params, context);
  }

  
  getBrigadeRequests(params?: GetBrigadeRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getBrigadeRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetBattalionsRequestsPath = '/api/brig-com/get/battalion-requests';

  
  getBattalionsRequests$Response(params?: GetBattalionsRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getBattalionsRequests(this.http, this.rootUrl, params, context);
  }

  
  getBattalionsRequests(params?: GetBattalionsRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getBattalionsRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  
  static readonly GetBrigadeResourcesPath = '/api/brig-com/get-brigade-resources';

  
  getBrigadeResources$Response(params?: GetBrigadeResources$Params, context?: HttpContext): Observable<StrictHttpResponse<BrigadeGroupDto>> {
    return getBrigadeResources(this.http, this.rootUrl, params, context);
  }

  
  getBrigadeResources(params?: GetBrigadeResources$Params, context?: HttpContext): Observable<BrigadeGroupDto> {
    return this.getBrigadeResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<BrigadeGroupDto>): BrigadeGroupDto => r.body)
    );
  }

  static readonly GetBattalionGroupsPath = '/api/brig-com/brigade-battalion-groups';

  
  getBattalionGroups$Response(params?: GetBattalionGroups$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<BattalionGroupDto>>> {
    return getBattalionGroups(this.http, this.rootUrl, params, context);
  }

  
  getBattalionGroups(params?: GetBattalionGroups$Params, context?: HttpContext): Observable<Array<BattalionGroupDto>> {
    return this.getBattalionGroups$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<BattalionGroupDto>>): Array<BattalionGroupDto> => r.body)
    );
  }

}
