import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {TokenService} from "../service/token.service";

@Injectable()
export class GuardInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {
  }

  intercept(request: HttpRequest<unknown>,
            next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.tokenService.getToken() != null) {
      const token = this.tokenService.getToken();
      // @ts-ignore
      // const headers = new HttpHeaders().set('Authorization', 'Bearer ' + token); // cách 1
      const AuthRequest = request.clone({headers: request.headers.set('Authorization','Bearer ' + token)});// cách 2
      // const AuthRequest = request.clone({headers});
      return next.handle(AuthRequest);
    } else {
      return next.handle(request);
    }

  }
}
