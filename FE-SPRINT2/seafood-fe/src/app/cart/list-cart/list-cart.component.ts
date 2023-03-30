import { Component, OnInit } from '@angular/core';
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {TokenService} from "../../service/token.service";
import {SizeService} from "../../service/size.service";
import {Size} from "../../model/size";
import {MessageService} from "../../service/message.service";
import Swal from "sweetalert2";
import { render } from 'creditcardpayments/creditCardPayments';

@Component({
  selector: 'app-list-cart',
  templateUrl: './list-cart.component.html',
  styleUrls: ['./list-cart.component.css']
})
export class ListCartComponent implements OnInit {
  carts: any[] = []
  price = 0;
  totalPrice = 0;
  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private messageService: MessageService) {
    this.getAllCart(this.tokenService.getId())
    render({
      id: "#buttonPayment",
      currency: "USD",
      value: "100.00",
      onApprove: (details) => {
        alert("Ok")
      }
    })
  }

  ngOnInit(): void {
    window.scrollTo(1900,700)
  }

  getAllCart(userId:any){
    this.totalPrice = 0;
    this.cartService.getAllCart(userId).subscribe(data=>{
      this.carts = data;
      console.log(this.carts)
      for (let i = 0; i < this.carts.length; i++){
        this.totalPrice += this.carts[i].price * this.carts[i].size * this.carts[i].quantity
      }
    })
  }

  updateQuantity(productId: any, size: any, quantity: any) {
    this.cartService.update(quantity, productId, size).subscribe(data=>{
      this.getAllCart(this.tokenService.getId())
    })
  }

  delete(id: any) {
    this.cartService.delete(id).subscribe(data=>{
      this.getAllCart(this.tokenService.getId())
      Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Xóa thành công!',
            showConfirmButton: false,
            timer: 3000
          });
    })
  }
}
