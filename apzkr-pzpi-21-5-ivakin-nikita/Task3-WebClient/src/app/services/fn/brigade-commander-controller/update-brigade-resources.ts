/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { BrigadeGroupDto } from '../../models/brigade-group-dto';
import { ResourcesUpdateResponse } from '../../models/resources-update-response';

export interface UpdateBrigadeResources$Params {
      body: BrigadeGroupDto
}

export function updateBrigadeResources(http: HttpClient, rootUrl: string, params: UpdateBrigadeResources$Params, context?: HttpContext): Observable<StrictHttpResponse<ResourcesUpdateResponse>> {
  const rb = new RequestBuilder(rootUrl, updateBrigadeResources.PATH, 'put');
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

updateBrigadeResources.PATH = '/api/brig-com/update/brigade-resources';
