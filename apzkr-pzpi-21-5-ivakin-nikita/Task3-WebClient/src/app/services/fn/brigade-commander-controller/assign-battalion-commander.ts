/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AssignBattalionCommander$Params {
  battalionCommanderId: number;
  battalionGroupId: number;
}

export function assignBattalionCommander(http: HttpClient, rootUrl: string, params: AssignBattalionCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
  const rb = new RequestBuilder(rootUrl, assignBattalionCommander.PATH, 'put');
  if (params) {
    rb.query('battalionCommanderId', params.battalionCommanderId, {});
    rb.query('battalionGroupId', params.battalionGroupId, {});
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

assignBattalionCommander.PATH = '/api/brig-com/assign/battalion-commander';
