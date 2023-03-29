import { Component, OnInit } from '@angular/core';
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-list-cart',
  templateUrl: './list-cart.component.html',
  styleUrls: ['./list-cart.component.css']
})
export class ListCartComponent implements OnInit {
  carts: any[] = []

  constructor(private cartService: CartService,
              private tokenService: TokenService) {
    this.getAllCart(this.tokenService.getId())
  }

  ngOnInit(): void {
    window.scrollTo(1900,700)
  }

  getAllCart(userId:any){
    this.cartService.getAllCart(userId).subscribe(data=>{
      this.carts = data;
      console.log(this.carts)
    })
  }
}
