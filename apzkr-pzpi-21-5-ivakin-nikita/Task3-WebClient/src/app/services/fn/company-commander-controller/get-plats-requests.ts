/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SupplyRequest } from '../../models/supply-request';

export interface GetPlatsRequests$Params {
}

export function getPlatsRequests(http: HttpClient, rootUrl: string, params?: GetPlatsRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
  const rb = new RequestBuilder(rootUrl, getPlatsRequests.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<SupplyRequest>>;
    })
  );
}

getPlatsRequests.PATH = '/api/com-com/get/plat-requests';
