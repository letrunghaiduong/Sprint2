import { Component, OnInit } from '@angular/core';
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {TokenService} from "../../service/token.service";
import {SizeService} from "../../service/size.service";
import {Size} from "../../model/size";
import {MessageService} from "../../service/message.service";
import Swal from "sweetalert2";
import { render } from 'creditcardpayments/creditCardPayments';
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {Local} from "protractor/built/driverProviders";
import {OrderService} from "../../service/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list-cart',
  templateUrl: './list-cart.component.html',
  styleUrls: ['./list-cart.component.css']
})
export class ListCartComponent implements OnInit {
  carts: any[] = []
  price = 0;
  totalPrice = 0;
  priceTotal: any;
  checkPayment = false;
  user: User = {}
  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private userService: UserService,
              private orderService: OrderService,
              private router: Router) {
    this.getAllCart(this.tokenService.getId())
    this.userService.findById(this.tokenService.getId()).subscribe(data=>{
      this.user = data;
      console.log(this.user)
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
      render({
        id: "#buttonPayment",
        currency: "USD",
        value: (this.totalPrice/23000).toFixed(2),
        onApprove: (details) => {
          for (let i = 0; i < this.carts.length; i++){
            this.orderService.order(this.user.address,
              this.carts[i].size*this.carts[i].quantity*this.carts[i].price,this.carts[i].id,this.user.id).subscribe(data=>{
            })
          }
          alert('oke')
          this.getAll()
        }
      })
    })
  }

  getAll(){
    this.cartService.getAllCart(this.tokenService.getId()).subscribe(data=>{
      this.totalPrice = 0
      this.carts = data
      if (this.carts.length > 0){
        for (let i = 0; i < this.carts.length; i++){
          this.totalPrice += this.carts[i].price * this.carts[i].size * this.carts[i].quantity
        }
      }

    })
  }


  updateQuantity(productId: any, size: any, quantity: any) {
    this.cartService.update(quantity, productId, size).subscribe(data=>{
      // this.getAllCart(this.tokenService.getId())
      this.getAll()
    })
  }

  delete(id: any) {
    this.cartService.delete(id).subscribe(data=>{
      this.getAll()
      Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Xóa thành công!',
            showConfirmButton: false,
            timer: 3000
          });
    })
  }


  payment() {
    this.checkPayment = true;
    window.scrollTo(0,1300)
  }

  setCheckPayment(){
    this.checkPayment = false;
    window.scrollTo(1900,700)

  }
}
