import {Component, OnInit} from '@angular/core';
import {ImageService} from "../../service/image.service";
import {SizeService} from "../../service/size.service";
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {ActivatedRoute, Router} from "@angular/router";
import {Image} from "../../model/image";
import {Size} from "../../model/size";
import {TokenService} from "../../service/token.service";
import Swal from "sweetalert2";
import {CartService} from "../../service/cart.service";
import {OrderDetail} from "../../model/order-detail";
import {UserService} from "../../service/user.service";
import {User} from "../../model/user";
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {MessageService} from "../../service/message.service";
import {render} from "creditcardpayments/creditCardPayments";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Title} from "@angular/platform-browser";

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
  id = 0
  product: Product = {}
  size:any = {}

  form: FormGroup;
  quantity: any;

  constructor(private imageService: ImageService,
              private sizeService: SizeService,
              private productService: ProductService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenService,
              private cartService: CartService,
              private router:Router,
              private lenghtMessage: LenghtMessageService,
              private title: Title) {
    this.activatedRoute.paramMap.subscribe(next => {
      // @ts-ignore
      this.id = +next.get('id');
      this.getProduct(this.id);
      this.getImage(this.id)
      this.getSize(this.id)
    });
    this.form = new FormGroup({
      size: new FormControl('',[Validators.required]),
      quantity: new FormControl('',[Validators.required,Validators.min(1)]),
    });
  }

  ngOnInit(): void {
    window.scrollTo(1900, 660)
    this.title.setTitle('Xem chi tiết')
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
    this.totalPrice = this.selectedSize * this.price * this.quantity;

  }


  addToCart() {
    if (this.form.valid){
      const size = this.form.value.size;
      const quantity = this.form.value.quantity;
      this.cartService.addToCart(this.product.id, this.tokenService.getId(),quantity,size).subscribe(data => {
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'Đã thêm vào giỏ hàng!',
          showConfirmButton: false,
          timer: 3000
        });
        this.cartService.getAllCart(this.tokenService.getId()).subscribe(data=>{
          this.lenghtMessage.changeMassege(data.length);
        })
      },error => {
        if (error.error==='errorQuantity'){
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Số lượng trong kho không đủ!',
            showConfirmButton: false,
            timer: 3000
          });
        }else if (error.error ==='errorSize') {
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Bạn chưa chọn kích thước!',
            showConfirmButton: false,
            timer: 3000
          })
        }else if (error.error ==='errorLogin') {
          Swal.fire({
            position: 'center',
            icon: 'warning',
            title: 'Bạn phải đăng nhập để tiếp tục!',
            showCancelButton: true,
            cancelButtonText: 'Hủy',
            confirmButtonText: 'Đăng nhập',
            timer: 3000
          }).then((result) => {
            if (result.isConfirmed) {
              this.router.navigateByUrl('/login')
            }
          })
        }
      })
    }
  }
}
