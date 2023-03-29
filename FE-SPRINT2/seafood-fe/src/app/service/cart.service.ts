import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {OrderProduct} from "../model/order-product";
import {OrderDetail} from "../model/order-detail";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  API_CART = 'http://localhost:8080/api/orderDetail'
  constructor(private httpClient: HttpClient) { }

  getAllCart(userId: any): Observable<any>{
   return  this.httpClient.get<OrderDetail[]>(this.API_CART + '/list?userId=' + userId)
  }

  addToCart(productId: any, userId: any, size: any): Observable<OrderDetail>{
    return this.httpClient.get(this.API_CART + '/add?productId=' + productId + '&userId=' + userId+'&size='+size)
  }

}
