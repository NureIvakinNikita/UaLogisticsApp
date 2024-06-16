/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SupplyRequest } from '../../models/supply-request';

export interface GetBrigadeRequests$Params {
}

export function getBrigadeRequests(http: HttpClient, rootUrl: string, params?: GetBrigadeRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
  const rb = new RequestBuilder(rootUrl, getBrigadeRequests.PATH, 'get');
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

getBrigadeRequests.PATH = '/api/brig-com/get/brigade-requests';
