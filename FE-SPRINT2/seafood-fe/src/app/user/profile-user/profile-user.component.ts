import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/token.service";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {OrderService} from "../../service/order.service";
import {OrderProduct} from "../../model/order-product";
import {Purchase} from "../../model/purchase";

@Component({
  selector: 'app-profile-user',
  templateUrl: './profile-user.component.html',
  styleUrls: ['./profile-user.component.css']
})
export class ProfileUserComponent implements OnInit {
  profile = true;
  purchase = false;
  changePass = false;

  orderList: OrderProduct[] = []
  detailList: Purchase[] = []


  user: User = {}
  constructor(private tokenService: TokenService,
              private userService: UserService,
              private orderService: OrderService) {
    this.userService.findById(this.tokenService.getId()).subscribe(data=>{
      this.user = data
    })
    this.orderService.purchaseHistories(this.tokenService.getId()).subscribe(data=>{
      this.orderList = data
    })

  }

  ngOnInit(): void {
    window.scrollTo(1900, 600)

  }

  getProfile() {
    this.profile = true;
    this.purchase = false;
    this.changePass = false;
  }

  purchaseHistory() {
    this.profile = false;
    this.purchase = true;
    this.changePass = false;
  }

  changePassword() {

  }

  detailPurchase(code: any) {
    this.orderService.detailPurchase(code).subscribe(data=>{
      this.detailList = data;
    })
  }
}
