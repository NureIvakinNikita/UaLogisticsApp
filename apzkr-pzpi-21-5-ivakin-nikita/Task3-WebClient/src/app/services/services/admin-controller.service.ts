/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { changeIotUrl } from '../fn/admin-controller/change-iot-url';
import { ChangeIotUrl$Params } from '../fn/admin-controller/change-iot-url';
import { createPost } from '../fn/admin-controller/create-post';
import { CreatePost$Params } from '../fn/admin-controller/create-post';
import { createScanningDevice } from '../fn/admin-controller/create-scanning-device';
import { CreateScanningDevice$Params } from '../fn/admin-controller/create-scanning-device';
import { GetPosts$Params, getPosts } from '../fn/admin-controller/get-posts';
import { PostDto } from '../models';

@Injectable({ providedIn: 'root' })
export class AdminControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  static readonly CreateScanningDevicePath = '/api/admin/assign/scanning-device-for-post/{id}';


  createScanningDevice$Response(params: CreateScanningDevice$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createScanningDevice(this.http, this.rootUrl, params, context);
  }

  
  createScanningDevice(params: CreateScanningDevice$Params, context?: HttpContext): Observable<boolean> {
    return this.createScanningDevice$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  
  static readonly CreatePostPath = '/api/admin/create/post';

 
  createPost$Response(params: CreatePost$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return createPost(this.http, this.rootUrl, params, context);
  }

 
  createPost(params: CreatePost$Params, context?: HttpContext): Observable<boolean> {
    return this.createPost$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }

  
  static readonly ChangeIotUrlPath = '/api/admin/change/IoT-url';

  
  changeIotUrl$Response(params: ChangeIotUrl$Params, context?: HttpContext): Observable<StrictHttpResponse<boolean>> {
    return changeIotUrl(this.http, this.rootUrl, params, context);
  }

  
  changeIotUrl(params: ChangeIotUrl$Params, context?: HttpContext): Observable<boolean> {
    return this.changeIotUrl$Response(params, context).pipe(
      map((r: StrictHttpResponse<boolean>): boolean => r.body)
    );
  }


  static readonly GetPostsPath = '/api/admin/get/posts';

  getPosts$Response(params?: GetPosts$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PostDto>>> {
    return getPosts(this.http, this.rootUrl, params, context);
  }

  getPosts(params?: GetPosts$Params, context?: HttpContext): Observable<Array<PostDto>> {
    return this.getPosts$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<PostDto>>): Array<PostDto> => r.body)
    );
  }
  

}
