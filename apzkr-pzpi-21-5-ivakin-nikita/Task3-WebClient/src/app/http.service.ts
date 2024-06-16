import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { environment } from "../environments/environment.development";
import { ToastrService } from "ngx-toastr";


@Injectable({
    providedIn: 'root'
  })
  export class HttpService {
  
    private baseUrl = environment.apiUrl;
  
    constructor(private httpClient: HttpClient, private toastr: ToastrService) { }
  
    get<T>(url: string): Observable<T> {
      return this.httpClient.get<T>(this.buildUrl(url)).pipe(catchError((error) => this.handleError(error)));
    }
  
    post<T>(url: string, resource: unknown) {
      return this.httpClient.post<T>(this.buildUrl(url), resource).pipe(catchError((error) => this.handleError(error)));
    }
  
    delete(url: string, id: string | number) {
      return this.httpClient.delete(`${this.buildUrl(url)}/${id}`).pipe(catchError((error) => this.handleError(error)));
    }
  
    put<T>(url: string, resource: T) {
      return this.httpClient.put<T>(this.buildUrl(url), resource).pipe(catchError((error) => this.handleError(error)));
    }
  
    private handleError(err: HttpErrorResponse) {
      if (err?.error?.detail) {
        this.toastr.error(err.error.detail, 'Error!');
      } else {
        this.toastr.error(`Error ${err.status}!`);
      }
  
      return throwError(() => err);
    }
  
    private buildUrl(url: string): string {
      return this.baseUrl + url;
    }
  }
  