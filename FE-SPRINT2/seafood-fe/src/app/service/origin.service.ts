import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Origin} from "../model/origin";

@Injectable({
  providedIn: 'root'
})
export class OriginService {
  API_ORIGIN = 'http://localhost:8080/api/origin'

  constructor(private httpClient: HttpClient) { }
  getAll(){
    return  this.httpClient.get<Origin[]>(this.API_ORIGIN)
  }
}
