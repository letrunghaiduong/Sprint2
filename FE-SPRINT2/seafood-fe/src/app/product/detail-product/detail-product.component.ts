import {Component, OnInit} from '@angular/core';
import {ImageService} from "../../service/image.service";
import {SizeService} from "../../service/size.service";
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {ActivatedRoute} from "@angular/router";
import {Image} from "../../model/image";
import {Size} from "../../model/size";
import {TokenService} from "../../service/token.service";
import Swal from "sweetalert2";
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {
  totalPrice = 0;
  price: number | undefined = 0;
  selectedSize = 0;

  imageList: Image[] = []
  sizeList: Size[] = []
  id: number = 0
  product: Product = {}

  form: FormGroup;

  constructor(private imageService: ImageService,
              private sizeService: SizeService,
              private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenService,
              private cartService: CartService,
              private userService: UserService) {
    this.activatedRoute.paramMap.subscribe(next => {
      // @ts-ignore
      this.id = +next.get('id');
      this.getProduct(this.id);
      this.getImage(this.id)
      this.getSize(this.id)
    });
    this.form = new FormGroup({
      size: new FormControl('',Validators.required),
    });
  }

  ngOnInit(): void {
    window.scrollTo(1900, 660)
  }

  getProduct(productId: number) {
    this.productService.findById(productId).subscribe(data => {
      // @ts-ignore
      this.product = data;
      this.price = this.product.price;
    })
  }

  getSize(id: number) {
    this.sizeService.getAllSize(id).subscribe(data => {
      // @ts-ignore
      this.sizeList = data

    })
  }

  getImage(id: number) {
    this.imageService.getAllImage(id).subscribe(data => {
      // @ts-ignore
      this.imageList = data
    })
  }


  calculatePrice() {
    // @ts-ignore
    this.totalPrice = this.selectedSize * this.price;

  }


  addToCart() {
    const size = this.form.value.size;
    this.cartService.addToCart(this.product.id, this.tokenService.getId(),size).subscribe(data => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Đã thêm vào giỏ hàng!',
        showConfirmButton: false,
        timer: 3000
      });
    })
  }
}
