import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of, fromEvent, throwError } from 'rxjs';
import { switchMap, map, catchError } from 'rxjs/operators';

@Injectable()
export class InterceptorService implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      switchMap(event => {
        if (event instanceof HttpResponse && event.body instanceof Blob) {
          const reader = new FileReader();
          const obs = fromEvent(reader, 'load').pipe(
            map(() => {
              const responseText = reader.result as string;
              const response = JSON.parse(responseText);
              return event.clone({ body: response }) as HttpEvent<any>;
            })
          );
          reader.readAsText(event.body);
          return obs;
        }
        return of(event);
      }),
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof Blob) {
          const reader = new FileReader();
          const obs = fromEvent(reader, 'load').pipe(
            switchMap(() => {
              const errorText = reader.result as string;
              const errorResponse = JSON.parse(errorText);
              return throwError(() => new HttpErrorResponse({
                ...error,
                error: errorResponse,
                url: error.url || undefined
              }));
            })
          );
          reader.readAsText(error.error);
          return obs;
        }
        return throwError(() => error);
      })
    );
  }
}
