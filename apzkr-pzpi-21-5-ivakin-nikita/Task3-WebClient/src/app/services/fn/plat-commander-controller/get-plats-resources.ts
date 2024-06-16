/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PlatGroupDto } from '../../models/plat-group-dto';

export interface GetPlatsResources$Params {
}

export function getPlatsResources(http: HttpClient, rootUrl: string, params?: GetPlatsResources$Params, context?: HttpContext): Observable<StrictHttpResponse<PlatGroupDto>> {
  const rb = new RequestBuilder(rootUrl, getPlatsResources.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PlatGroupDto>;
    })
  );
}

getPlatsResources.PATH = '/api/plat-com/get-plat-resources';
