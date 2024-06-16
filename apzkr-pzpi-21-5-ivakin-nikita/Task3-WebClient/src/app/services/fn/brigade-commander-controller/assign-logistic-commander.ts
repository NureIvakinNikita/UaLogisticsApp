/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AssignLogisticCommander$Params {
  logisticCommanderId: number;
  logisticCompanyId: number;
}

export function assignLogisticCommander(http: HttpClient, rootUrl: string, params: AssignLogisticCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
  const rb = new RequestBuilder(rootUrl, assignLogisticCommander.PATH, 'put');
  if (params) {
    rb.query('logisticCommanderId', params.logisticCommanderId, {});
    rb.query('logisticCompanyId', params.logisticCompanyId, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: String((r as HttpResponse<any>).body) === 'true' }) as StrictHttpResponse<boolean>;
    })
  );
}

assignLogisticCommander.PATH = '/api/brig-com/assign/logistic-commander';
