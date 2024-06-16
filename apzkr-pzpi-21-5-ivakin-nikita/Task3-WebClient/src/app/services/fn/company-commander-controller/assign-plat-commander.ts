/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AssignPlatCommander$Params {
  platCommanderId: number;
  platGroupId: number;
}

export function assignPlatCommander(http: HttpClient, rootUrl: string, params: AssignPlatCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
  const rb = new RequestBuilder(rootUrl, assignPlatCommander.PATH, 'put');
  if (params) {
    rb.query('platCommanderId', params.platCommanderId, {});
    rb.query('platGroupId', params.platGroupId, {});
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

assignPlatCommander.PATH = '/api/com-com/assign/plat-commander';
