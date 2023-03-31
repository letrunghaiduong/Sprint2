import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  API_ORDER = 'http://localhost:8080/api/orderProduct'
  constructor(private httpClient: HttpClient) { }

  order(shippingAddress: any,totalPrice: any, orderDetailId: any, userId: any){
    // return this.httpClient.get(this.API_ORDER + 'add?oderDate=' + oderDate + '&shippingAddress='+ shippingAddress +
    // '&totalPrice=' + totalPrice + '&orderDetailId'+ orderDetailId + '&userId=' + userId)
    return this.httpClient.post(this.API_ORDER + '/add', {shippingAddress: shippingAddress,
      totalPrice: totalPrice, orderDetailId: orderDetailId ,userId: userId})
  }
}
