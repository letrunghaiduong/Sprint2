import {Component, OnInit} from '@angular/core';
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {TokenService} from "../../service/token.service";
import {SizeService} from "../../service/size.service";
import {Size} from "../../model/size";
import {MessageService} from "../../service/message.service";
import Swal from "sweetalert2";
import {render} from 'creditcardpayments/creditCardPayments';
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {Local} from "protractor/built/driverProviders";
import {OrderService} from "../../service/order.service";
import {Router} from "@angular/router";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-list-cart',
  templateUrl: './list-cart.component.html',
  styleUrls: ['./list-cart.component.css']
})
export class ListCartComponent implements OnInit {
  carts: any[] = []
  price = 0;
  totalPrice = 0;
  checkPayment = false;
  user: User = {}
  quantity: any;
  address: any
  formAddress: FormGroup = new FormGroup({
    address: new FormControl('',[Validators.required])
  })

  constructor(private cartService: CartService,
              private tokenService: TokenService,
              private userService: UserService,
              private orderService: OrderService,
              private router: Router,
              private lenghtMessage: LenghtMessageService,
              private title: Title) {
    this.getAllCart(this.tokenService.getId())
    this.userService.findById(this.tokenService.getId()).subscribe(data => {
      this.user = data;
      this.address = this.user.address
    })
  }

  ngOnInit(): void {
    window.scrollTo(1900, 700)
    this.title.setTitle('Giỏ hàng ')
  }

  getAllCart(userId: any) {
    this.totalPrice = 0;
    this.cartService.getAllCart(userId).subscribe(data => {
      this.carts = data;
      console.log(this.carts)
      for (let i = 0; i < this.carts.length; i++) {
        this.totalPrice += this.carts[i].price * this.carts[i].size * this.carts[i].quantity
      }
      render({
        id: "#buttonPayment",
        currency: "USD",
        value: (this.totalPrice / 23000).toFixed(2),
        onApprove: (details) => {
          this.buy()
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Đặt hàng thành công!',
            showConfirmButton: false,
            timer: 3000
          });
          this.getAll()
          this.router.navigateByUrl('/')
        }
      })
    })
  }

  buy() {
    const number = Math.floor(Math.random()*1000000000);
    const code ='MDH-' + number;

    for (let i = 0; i < this.carts.length; i++) {
      this.orderService.order(this.address,
        this.carts[i].size * this.carts[i].quantity * this.carts[i].price, this.carts[i].id,
        this.carts[i].productId, this.carts[i].size, this.carts[i].quantity, code).subscribe(data => {
      })
    }
    this.cartService.setFlagDelete(this.user.id).subscribe()
  }


  getAll() {
    this.totalPrice = 0
    this.cartService.getAllCart(this.tokenService.getId()).subscribe(data => {
      this.carts = data
      this.lenghtMessage.changeMassege(data.length);
      if (this.carts.length > 0) {
        for (let i = 0; i < this.carts.length; i++) {
          this.totalPrice += this.carts[i].price * this.carts[i].size * this.carts[i].quantity
        }
      }
    })
  }

  inputQuantity(quantity: any, productId: any,size:any) {
    this.cartService.inputQuantity(quantity, productId,this.tokenService.getId(),size).subscribe(data => {
      this.getAll()
    },error => {
      if (error.error==='errorMaxQuantity'){
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Số lượng trong kho không đủ!',
          showConfirmButton: false,
          timer: 3000
        });
      }else if (error.error === 'errorMinQuantity'){
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Số lượng phải lớn hơn 0!',
          showConfirmButton: false,
          timer: 3000
        });
      }
    })
  }



  updateQuantity(productId: any, size: any, quantity: any) {
    this.totalPrice = 0;
    this.cartService.update(quantity, productId, this.tokenService.getId(),size).subscribe(data => {
      this.getAll()
    },error => {
      if (error.error==='errorQuantity'){
        Swal.fire({
          position: 'center',
          icon: 'error',
          title: 'Số lượng trong kho không đủ!',
          showConfirmButton: false,
          timer: 3000
        });
      }
    })
  }

  delete(id: any) {
    this.cartService.delete(id).subscribe(data => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Xóa thành công!',
        showConfirmButton: false,
        timer: 3000
      });
      this.getAll()
    })
  }


  payment() {
    this.checkPayment = true;
    window.scrollTo(1900, 750)
  }

  setCheckPayment() {
    this.checkPayment = false;
    window.scrollTo(1900, 650)

  }



  getAddress(value: string) {
    this.address = value
  }

}
