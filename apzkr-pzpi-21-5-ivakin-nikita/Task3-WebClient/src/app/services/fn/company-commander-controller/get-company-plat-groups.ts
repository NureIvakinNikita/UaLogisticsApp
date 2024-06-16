/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PlatGroupDto } from '../../models/plat-group-dto';

export interface GetCompanyPlatGroups$Params {
}

export function getCompanyPlatGroups(http: HttpClient, rootUrl: string, params?: GetCompanyPlatGroups$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PlatGroupDto>>> {
  const rb = new RequestBuilder(rootUrl, getCompanyPlatGroups.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<PlatGroupDto>>;
    })
  );
}

getCompanyPlatGroups.PATH = '/api/com-com/company-plat-groups';
