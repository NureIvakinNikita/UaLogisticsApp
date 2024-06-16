/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { askForResources1 } from '../fn/company-commander-controller/ask-for-resources-1';
import { AskForResources1$Params } from '../fn/company-commander-controller/ask-for-resources-1';
import { assignPlatCommander } from '../fn/company-commander-controller/assign-plat-commander';
import { AssignPlatCommander$Params } from '../fn/company-commander-controller/assign-plat-commander';
import { CompanyGroupDto } from '../models/company-group-dto';
import { confirmGettingOfResources1 } from '../fn/company-commander-controller/confirm-getting-of-resources-1';
import { ConfirmGettingOfResources1$Params } from '../fn/company-commander-controller/confirm-getting-of-resources-1';
import { createPlat } from '../fn/company-commander-controller/create-plat';
import { CreatePlat$Params } from '../fn/company-commander-controller/create-plat';
import { getCompanyPlatGroups } from '../fn/company-commander-controller/get-company-plat-groups';
import { GetCompanyPlatGroups$Params } from '../fn/company-commander-controller/get-company-plat-groups';
import { getCompanyRequests } from '../fn/company-commander-controller/get-company-requests';
import { GetCompanyRequests$Params } from '../fn/company-commander-controller/get-company-requests';
import { getCompanyResources } from '../fn/company-commander-controller/get-company-resources';
import { GetCompanyResources$Params } from '../fn/company-commander-controller/get-company-resources';
import { getPlatsRequests } from '../fn/company-commander-controller/get-plats-requests';
import { GetPlatsRequests$Params } from '../fn/company-commander-controller/get-plats-requests';
import { PlatGroupDto } from '../models/plat-group-dto';
import { ResourcesUpdateResponse } from '../models/resources-update-response';
import { sendResources } from '../fn/company-commander-controller/send-resources';
import { SendResources$Params } from '../fn/company-commander-controller/send-resources';
import { SupplyRequest } from '../models/supply-request';
import { updateCompanyResources } from '../fn/company-commander-controller/update-company-resources';
import { UpdateCompanyResources$Params } from '../fn/company-commander-controller/update-company-resources';

@Injectable({ providedIn: 'root' })
export class CompanyCommanderControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly UpdateCompanyResourcesPath = '/api/com-com/update/company-resources';

  
  updateCompanyResources$Response(params: UpdateCompanyResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return updateCompanyResources(this.http, this.rootUrl, params, context);
  }


  updateCompanyResources(params: UpdateCompanyResources$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.updateCompanyResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly ConfirmGettingOfResources1Path = '/api/com-com/confirm/getting-of-resources';

 confirmGettingOfResources1$Response(params: ConfirmGettingOfResources1$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return confirmGettingOfResources1(this.http, this.rootUrl, params, context);
  }

  confirmGettingOfResources1(params: ConfirmGettingOfResources1$Params, context?: HttpContext): Observable<boolean> {
    return this.confirmGettingOfResources1$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AssignPlatCommanderPath = '/api/com-com/assign/plat-commander';

  assignPlatCommander$Response(params: AssignPlatCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return assignPlatCommander(this.http, this.rootUrl, params, context);
  }

  assignPlatCommander(params: AssignPlatCommander$Params, context?: HttpContext): Observable<boolean> {
    return this.assignPlatCommander$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly SendResourcesPath = '/api/com-com/send/resources-to-plat';

  sendResources$Response(params: SendResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return sendResources(this.http, this.rootUrl, params, context);
  }

  sendResources(params: SendResources$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.sendResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly CreatePlatPath = '/api/com-com/create/plat';

  createPlat$Response(params: CreatePlat$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createPlat(this.http, this.rootUrl, params, context);
  }

  createPlat(params: CreatePlat$Params, context?: HttpContext): Observable<boolean> {
    return this.createPlat$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AskForResources1Path = '/api/com-com/ask/for-resources';

  askForResources1$Response(params: AskForResources1$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return askForResources1(this.http, this.rootUrl, params, context);
  }

  askForResources1(params: AskForResources1$Params, context?: HttpContext): Observable<boolean> {
    return this.askForResources1$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly GetPlatsRequestsPath = '/api/com-com/get/plat-requests';

  getPlatsRequests$Response(params?: GetPlatsRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getPlatsRequests(this.http, this.rootUrl, params, context);
  }

  getPlatsRequests(params?: GetPlatsRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getPlatsRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetCompanyRequestsPath = '/api/com-com/get/company-requests';

  getCompanyRequests$Response(params?: GetCompanyRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getCompanyRequests(this.http, this.rootUrl, params, context);
  }

  getCompanyRequests(params?: GetCompanyRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getCompanyRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetCompanyResourcesPath = '/api/com-com/get-company-resources';

  getCompanyResources$Response(params?: GetCompanyResources$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyGroupDto>> {
    return getCompanyResources(this.http, this.rootUrl, params, context);
  }

  getCompanyResources(params?: GetCompanyResources$Params, context?: HttpContext): Observable<CompanyGroupDto> {
    return this.getCompanyResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<CompanyGroupDto>): CompanyGroupDto => r.body)
    );
  }

  static readonly GetCompanyPlatGroupsPath = '/api/com-com/company-plat-groups';

  getCompanyPlatGroups$Response(params?: GetCompanyPlatGroups$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PlatGroupDto>>> {
    return getCompanyPlatGroups(this.http, this.rootUrl, params, context);
  }

  getCompanyPlatGroups(params?: GetCompanyPlatGroups$Params, context?: HttpContext): Observable<Array<PlatGroupDto>> {
    return this.getCompanyPlatGroups$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<PlatGroupDto>>): Array<PlatGroupDto> => r.body)
    );
  }

}
