/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ResourcesUpdateResponse } from '../../models/resources-update-response';
import { SupplyRequest } from '../../models/supply-request';

export interface SendResources$Params {
      body: SupplyRequest
}

export function sendResources(http: HttpClient, rootUrl: string, params: SendResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
  const rb = new RequestBuilder(rootUrl, sendResources.PATH, 'post');
  if (params) {
    rb.body(params.body, 'application/json');
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<ResourcesUpdateResponse>;
    })
  );
}

sendResources.PATH = '/api/com-com/send/resources-to-plat';
