/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface AssignCompanyCommander$Params {
  companyCommanderId: number;
  companyGroupId: number;
}

export function assignCompanyCommander(http: HttpClient, rootUrl: string, params: AssignCompanyCommander$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
  const rb = new RequestBuilder(rootUrl, assignCompanyCommander.PATH, 'put');
  if (params) {
    rb.query('companyCommanderId', params.companyCommanderId, {});
    rb.query('companyGroupId', params.companyGroupId, {});
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

assignCompanyCommander.PATH = '/api/bat-com/assign/company-commander';
