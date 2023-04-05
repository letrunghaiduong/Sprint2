import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {OrderProduct} from "../model/order-product";
import {Purchase} from "../model/purchase";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  API_ORDER = 'http://localhost:8080/api/orderProduct'
  constructor(private httpClient: HttpClient) { }

  order(shippingAddress: any,totalPrice: any, orderDetailId: any,productId: any, size: any, quantity: any, code: any){
    return this.httpClient.post(this.API_ORDER + '/add', {shippingAddress: shippingAddress,
      totalPrice: totalPrice, orderDetailId: orderDetailId, productId: productId,size: size, quantity: quantity, code: code})
  }

  purchaseHistories(userId: any){
    return this.httpClient.get<OrderProduct[]>(this.API_ORDER +'/list?userId=' + userId )
  }

  detailPurchase(code: any){
    return this.httpClient.get<Purchase[]>(this.API_ORDER +'/detailPurchase?code=' + code )
  }
}
