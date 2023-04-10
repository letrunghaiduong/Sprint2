import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/token.service";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {OrderService} from "../../service/order.service";
import {OrderProduct} from "../../model/order-product";
import {Purchase} from "../../model/purchase";
import {FormControl, FormGroup} from "@angular/forms";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {Title} from "@angular/platform-browser";

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
  orderPage: any;

  user: User = {}
  formUpdate: FormGroup = new FormGroup({
    id: new FormControl(),
    name: new FormControl(),
    avatar: new FormControl(),
    userName: new FormControl(),
    password: new FormControl(),
    phoneNumber: new FormControl(),
    email: new FormControl(),
    address: new FormControl(),
    dateOfBirth: new FormControl(),
    gender: new FormControl(),
  });

  constructor(private tokenService: TokenService,
              private userService: UserService,
              private orderService: OrderService,
              private title: Title) {
    this.userService.findById(this.tokenService.getId()).subscribe(data=>{
      this.user = data
      this.formUpdate.patchValue(this.user);

    })
    this.orderService.purchaseHistories(this.tokenService.getId()).subscribe(data=>{
      this.orderList = data
    })
  }

  ngOnInit(): void {
    window.scrollTo(1900, 600)
    this.title.setTitle('Thông tin cá nhân')
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

  edit() {

  }
}
