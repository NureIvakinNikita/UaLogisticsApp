/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { askForResources3 } from '../fn/battalion-commander-controller/ask-for-resources-3';
import { AskForResources3$Params } from '../fn/battalion-commander-controller/ask-for-resources-3';
import { assignCompanyCommander } from '../fn/battalion-commander-controller/assign-company-commander';
import { AssignCompanyCommander$Params } from '../fn/battalion-commander-controller/assign-company-commander';
import { BattalionGroupDto } from '../models/battalion-group-dto';
import { CompanyGroupDto } from '../models/company-group-dto';
import { confirmGettingOfResources3 } from '../fn/battalion-commander-controller/confirm-getting-of-resources-3';
import { ConfirmGettingOfResources3$Params } from '../fn/battalion-commander-controller/confirm-getting-of-resources-3';
import { createCompany } from '../fn/battalion-commander-controller/create-company';
import { CreateCompany$Params } from '../fn/battalion-commander-controller/create-company';
import { getBattalionCompanyGroups } from '../fn/battalion-commander-controller/get-battalion-company-groups';
import { GetBattalionCompanyGroups$Params } from '../fn/battalion-commander-controller/get-battalion-company-groups';
import { getBattalionRequests } from '../fn/battalion-commander-controller/get-battalion-requests';
import { GetBattalionRequests$Params } from '../fn/battalion-commander-controller/get-battalion-requests';
import { getBattalionResources } from '../fn/battalion-commander-controller/get-battalion-resources';
import { GetBattalionResources$Params } from '../fn/battalion-commander-controller/get-battalion-resources';
import { getCompaniesRequests } from '../fn/battalion-commander-controller/get-companies-requests';
import { GetCompaniesRequests$Params } from '../fn/battalion-commander-controller/get-companies-requests';
import { ResourcesUpdateResponse } from '../models/resources-update-response';
import { sendResources2 } from '../fn/battalion-commander-controller/send-resources-2';
import { SendResources2$Params } from '../fn/battalion-commander-controller/send-resources-2';
import { SupplyRequest } from '../models/supply-request';
import { updateBattalionResources } from '../fn/battalion-commander-controller/update-battalion-resources';
import { UpdateBattalionResources$Params } from '../fn/battalion-commander-controller/update-battalion-resources';

@Injectable({ providedIn: 'root' })
export class BattalionCommanderControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly UpdateBattalionResourcesPath = '/api/bat-com/update/battalion-resources';

  
  updateBattalionResources$Response(params: UpdateBattalionResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return updateBattalionResources(this.http, this.rootUrl, params, context);
  }

  
  updateBattalionResources(params: UpdateBattalionResources$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.updateBattalionResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly ConfirmGettingOfResources3Path = '/api/bat-com/confirm/getting-of-resources';

  
  confirmGettingOfResources3$Response(params: ConfirmGettingOfResources3$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return confirmGettingOfResources3(this.http, this.rootUrl, params, context);
  }

  
  confirmGettingOfResources3(params: ConfirmGettingOfResources3$Params, context?: HttpContext): Observable<boolean> {
    return this.confirmGettingOfResources3$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AssignCompanyCommanderPath = '/api/bat-com/assign/company-commander';


  assignCompanyCommander$Response(params: AssignCompanyCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return assignCompanyCommander(this.http, this.rootUrl, params, context);
  }


  assignCompanyCommander(params: AssignCompanyCommander$Params, context?: HttpContext): Observable<boolean> {
    return this.assignCompanyCommander$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly SendResources2Path = '/api/bat-com/send/resources-to-company';

  sendResources2$Response(params: SendResources2$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return sendResources2(this.http, this.rootUrl, params, context);
  }

  sendResources2(params: SendResources2$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.sendResources2$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly CreateCompanyPath = '/api/bat-com/create/company';

  createCompany$Response(params: CreateCompany$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createCompany(this.http, this.rootUrl, params, context);
  }

  createCompany(params: CreateCompany$Params, context?: HttpContext): Observable<boolean> {
    return this.createCompany$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AskForResources3Path = '/api/bat-com/ask/for-resources';
  askForResources3$Response(params: AskForResources3$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return askForResources3(this.http, this.rootUrl, params, context);
  }

  askForResources3(params: AskForResources3$Params, context?: HttpContext): Observable<boolean> {
    return this.askForResources3$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly GetCompaniesRequestsPath = '/api/bat-com/get/company-requests';

  getCompaniesRequests$Response(params?: GetCompaniesRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getCompaniesRequests(this.http, this.rootUrl, params, context);
  }

  getCompaniesRequests(params?: GetCompaniesRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getCompaniesRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetBattalionRequestsPath = '/api/bat-com/get/battalion-requests';

  getBattalionRequests$Response(params?: GetBattalionRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getBattalionRequests(this.http, this.rootUrl, params, context);
  }


  getBattalionRequests(params?: GetBattalionRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getBattalionRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetBattalionResourcesPath = '/api/bat-com/get-battalion-resources';

  getBattalionResources$Response(params?: GetBattalionResources$Params, context?: HttpContext): Observable<StrictHttpResponse<BattalionGroupDto>> {
    return getBattalionResources(this.http, this.rootUrl, params, context);
  }

  getBattalionResources(params?: GetBattalionResources$Params, context?: HttpContext): Observable<BattalionGroupDto> {
    return this.getBattalionResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<BattalionGroupDto>): BattalionGroupDto => r.body)
    );
  }

  static readonly GetBattalionCompanyGroupsPath = '/api/bat-com/battalion-company-groups';

  getBattalionCompanyGroups$Response(params?: GetBattalionCompanyGroups$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CompanyGroupDto>>> {
    return getBattalionCompanyGroups(this.http, this.rootUrl, params, context);
  }

  getBattalionCompanyGroups(params?: GetBattalionCompanyGroups$Params, context?: HttpContext): Observable<Array<CompanyGroupDto>> {
    return this.getBattalionCompanyGroups$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CompanyGroupDto>>): Array<CompanyGroupDto> => r.body)
    );
  }

}
