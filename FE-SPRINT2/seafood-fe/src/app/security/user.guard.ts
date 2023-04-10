import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {TokenService} from "../service/token.service";

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(private token:TokenService,private router:Router) {
  }
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.token.getToken()) {
      if (this.token.getRole()[0] == 'USER'||this.token.getRole()[0] == 'ADMIN') {
        return true;
      } else {
        this.router.navigateByUrl('/error')
        return false;
      }
    } else {
      this.router.navigateByUrl('/error')
      return false;
    }
  }
}
