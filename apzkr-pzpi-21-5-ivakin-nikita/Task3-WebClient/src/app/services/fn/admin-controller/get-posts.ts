import { HttpClient, HttpContext, HttpResponse } from "@angular/common/http";
import { Observable, filter, map } from "rxjs";
import { RequestBuilder } from "../../request-builder";
import { StrictHttpResponse } from "../../strict-http-response";
import { PostDto } from "../../models";

export interface GetPosts$Params {
}

export function getPosts(http: HttpClient, rootUrl: string, params?: GetPosts$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PostDto>>> {
    const rb = new RequestBuilder(rootUrl, getPosts.PATH, 'get');
    if (params) {
    }
  
    return http.request(
      rb.build({ responseType: 'blob', accept: '*/*', context })
    ).pipe(
      filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<PostDto>>;
      })
    );
  }
  
  getPosts.PATH = '/api/admin/get/posts';
  