/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PlatGroupDto } from '../../models/plat-group-dto';
import { ResourcesUpdateResponse } from '../../models/resources-update-response';

export interface UpdatePlatResources$Params {
      body: PlatGroupDto
}

export function updatePlatResources(http: HttpClient, rootUrl: string, params: UpdatePlatResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
  const rb = new RequestBuilder(rootUrl, updatePlatResources.PATH, 'put');
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

updatePlatResources.PATH = '/api/plat-com/update/plat-resources';
