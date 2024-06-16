/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface ConfirmGettingOfResources1$Params {
  supplyRequestId: number;
}

export function confirmGettingOfResources1(http: HttpClient, rootUrl: string, params: ConfirmGettingOfResources1$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
  const rb = new RequestBuilder(rootUrl, confirmGettingOfResources1.PATH, 'put');
  if (params) {
    rb.query('supplyRequestId', params.supplyRequestId, {});
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

confirmGettingOfResources1.PATH = '/api/com-com/confirm/getting-of-resources';
