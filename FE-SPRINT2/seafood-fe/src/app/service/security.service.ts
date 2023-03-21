import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {SignInForm} from "../model/dto/SignInForm";
import {Observable} from "rxjs";
import {JwtResponse} from "../model/dto/JwtResponse";

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  API_SIGNIN ='http://localhost:8080/api/public/signin'

  constructor(private httpClient: HttpClient) { }

  signIn(signInForm: SignInForm):Observable<JwtResponse>{
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN,signInForm);
  }

}
