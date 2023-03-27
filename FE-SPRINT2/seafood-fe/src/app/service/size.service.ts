import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Size} from "../model/size";

@Injectable({
  providedIn: 'root'
})
export class SizeService {
  API_SIZE = 'http://localhost:8080/api/size/'
  constructor(private httpClient: HttpClient) { }

  getAllSize(productId: number){
    return  this.httpClient.get<Size>(this.API_SIZE + 'list?productId='+ productId);
  }
}
