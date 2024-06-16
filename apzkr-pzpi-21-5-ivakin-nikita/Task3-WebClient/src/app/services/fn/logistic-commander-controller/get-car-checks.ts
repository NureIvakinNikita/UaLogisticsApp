/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CarCheckDto } from '../../models/car-check-dto';

export interface GetCarChecks$Params {
  id: number;
}

export function getCarChecks(http: HttpClient, rootUrl: string, params: GetCarChecks$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CarCheckDto>>> {
  const rb = new RequestBuilder(rootUrl, getCarChecks.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<CarCheckDto>>;
    })
  );
}

getCarChecks.PATH = '/api/log-com/get/car-checks/{id}';
