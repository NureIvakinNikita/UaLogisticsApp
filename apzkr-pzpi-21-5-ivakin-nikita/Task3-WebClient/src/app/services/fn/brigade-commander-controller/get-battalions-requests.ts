/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { SupplyRequest } from '../../models/supply-request';

export interface GetBattalionsRequests$Params {
}

export function getBattalionsRequests(http: HttpClient, rootUrl: string, params?: GetBattalionsRequests$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<SupplyRequest>>> {
  const rb = new RequestBuilder(rootUrl, getBattalionsRequests.PATH, 'get');
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

getBattalionsRequests.PATH = '/api/brig-com/get/battalion-requests';
