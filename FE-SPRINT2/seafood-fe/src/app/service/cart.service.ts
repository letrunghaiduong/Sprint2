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

  addToCart(productId: any, userId: any, quantity: any,size: any): Observable<OrderDetail>{
    return this.httpClient.post(this.API_CART + '/add',{productId: productId, userId: userId, quantity: quantity, size: size})
  }

  update(quantity: any, productId: any, userId: any,size:any): Observable<OrderDetail>{
    return this.httpClient.put(this.API_CART + '/update', {productId: productId, userId: userId, quantity: quantity, size: size})
  }

  inputQuantity(quantity: any, productId: any, userId: any,size:any): Observable<OrderDetail>{
    return this.httpClient.put(this.API_CART + '/inputQuantity',{productId: productId, userId: userId, quantity: quantity, size: size})
  }

  delete(id: any): Observable<OrderDetail>{
    return this.httpClient.delete(this.API_CART + '/delete?id=' + id  )
  }

  setFlagDelete(userId: any): Observable<OrderDetail>{
    return this.httpClient.get(this.API_CART + '/setFlagDelete?userId=' + userId)
  }
}
