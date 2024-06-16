/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CompanyGroupDto } from '../../models/company-group-dto';

export interface GetCompanyResources$Params {
}

export function getCompanyResources(http: HttpClient, rootUrl: string, params?: GetCompanyResources$Params, context?: HttpContext): Observable<StrictHttpResponse<CompanyGroupDto>> {
  const rb = new RequestBuilder(rootUrl, getCompanyResources.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<CompanyGroupDto>;
    })
  );
}

getCompanyResources.PATH = '/api/com-com/get-company-resources';
