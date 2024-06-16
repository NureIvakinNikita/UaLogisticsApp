/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { askForResources } from '../fn/plat-commander-controller/ask-for-resources';
import { AskForResources$Params } from '../fn/plat-commander-controller/ask-for-resources';
import { confirmGettingOfResources } from '../fn/plat-commander-controller/confirm-getting-of-resources';
import { ConfirmGettingOfResources$Params } from '../fn/plat-commander-controller/confirm-getting-of-resources';
import { getPlatRequests } from '../fn/plat-commander-controller/get-plat-requests';
import { GetPlatRequests$Params } from '../fn/plat-commander-controller/get-plat-requests';
import { getPlatsResources } from '../fn/plat-commander-controller/get-plats-resources';
import { GetPlatsResources$Params } from '../fn/plat-commander-controller/get-plats-resources';
import { PlatGroupDto } from '../models/plat-group-dto';
import { ResourcesUpdateResponse } from '../models/resources-update-response';
import { SupplyRequest } from '../models/supply-request';
import { updatePlatResources } from '../fn/plat-commander-controller/update-plat-resources';
import { UpdatePlatResources$Params } from '../fn/plat-commander-controller/update-plat-resources';

@Injectable({ providedIn: 'root' })
export class PlatCommanderControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly UpdatePlatResourcesPath = '/api/plat-com/update/plat-resources';

  updatePlatResources$Response(params: UpdatePlatResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
    return updatePlatResources(this.http, this.rootUrl, params, context);
  }

  updatePlatResources(params: UpdatePlatResources$Params, context?: HttpContext): Observable<ResourcesUpdateResponse> {
    return this.updatePlatResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<ResourcesUpdateResponse>): ResourcesUpdateResponse => r.body)
    );
  }

  static readonly ConfirmGettingOfResourcesPath = '/api/plat-com/confirm/getting-of-resources';

  confirmGettingOfResources$Response(params: ConfirmGettingOfResources$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return confirmGettingOfResources(this.http, this.rootUrl, params, context);
  }

  confirmGettingOfResources(params: ConfirmGettingOfResources$Params, context?: HttpContext): Observable<boolean> {
    return this.confirmGettingOfResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly AskForResourcesPath = '/api/plat-com/ask/for-resources';

  askForResources$Response(params: AskForResources$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return askForResources(this.http, this.rootUrl, params, context);
  }

  askForResources(params: AskForResources$Params, context?: HttpContext): Observable<boolean> {
    return this.askForResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  static readonly GetPlatRequestsPath = '/api/plat-com/get/plat-requests';

  getPlatRequests$Response(params?: GetPlatRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
    return getPlatRequests(this.http, this.rootUrl, params, context);
  }

  getPlatRequests(params?: GetPlatRequests$Params, context?: HttpContext): Observable<Array<SupplyRequest>> {
    return this.getPlatRequests$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<SupplyRequest>>): Array<SupplyRequest> => r.body)
    );
  }

  static readonly GetPlatsResourcesPath = '/api/plat-com/get-plat-resources';

  getPlatsResources$Response(params?: GetPlatsResources$Params, context?: HttpContext): Observable<StrictHttpResponse<PlatGroupDto>> {
    return getPlatsResources(this.http, this.rootUrl, params, context);
  }

  getPlatsResources(params?: GetPlatsResources$Params, context?: HttpContext): Observable<PlatGroupDto> {
    return this.getPlatsResources$Response(params, context).pipe(
      map((r: StrictHttpResponse<PlatGroupDto>): PlatGroupDto => r.body)
    );
  }

}
