import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  API_ORDER = 'http://localhost:8080/api/orderProduct'
  constructor(private httpClient: HttpClient) { }

  order(shippingAddress: any,totalPrice: any, orderDetailId: any,productId: any, size: any, quantity: any){
    return this.httpClient.post(this.API_ORDER + '/add', {shippingAddress: shippingAddress,
      totalPrice: totalPrice, orderDetailId: orderDetailId, productId: productId,size: size, quantity: quantity})
  }
}
