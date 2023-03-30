import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  API_PRODUCT = 'http://localhost:8080/api/product'

  constructor(private httpClient: HttpClient) { }

  getAll(search: string, size: number){
   return  this.httpClient.get<Product[]>(this.API_PRODUCT+'/list?search='+search+'&size='+size)
  }

  findById(productId: number){
    return this.httpClient.get<Product>(this.API_PRODUCT+ '/findById?productId='+productId)
  }

}
