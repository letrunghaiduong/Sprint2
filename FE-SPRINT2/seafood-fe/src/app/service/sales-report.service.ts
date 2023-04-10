import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SalesReport} from "../model/sales-report";

@Injectable({
  providedIn: 'root'
})
export class SalesReportService {
  API_SALE = 'http://localhost:8080/api/saleReport/'
  constructor(private httpClient: HttpClient) { }

  saleReport(){
    return  this.httpClient.get<SalesReport[]>(this.API_SALE)
  }
}
