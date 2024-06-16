/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BrigadeGroupDto } from '../../models/brigade-group-dto';

export interface GetBrigadeResources$Params {
}

export function getBrigadeResources(http: HttpClient, rootUrl: string, params?: GetBrigadeResources$Params, context?: HttpContext): Observable<StrictHttpResponse<BrigadeGroupDto>> {
  const rb = new RequestBuilder(rootUrl, getBrigadeResources.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<BrigadeGroupDto>;
    })
  );
}

getBrigadeResources.PATH = '/api/brig-com/get-brigade-resources';
