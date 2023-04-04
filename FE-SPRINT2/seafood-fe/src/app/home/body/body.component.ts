import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import Swal from "sweetalert2";
import {TokenService} from "../../service/token.service";
import {MessageService} from "../../service/message.service";
import {CartService} from "../../service/cart.service";
import {Router} from "@angular/router";
import {LenghtMessageService} from "../../service/lenght-message.service";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  search: string = ''
  productList: Product[] = []
  last: any;
  first: any;
  size = 4;

  constructor(private productService: ProductService,
              private messageService: MessageService,
              private tokenService: TokenService,
              private cartService: CartService,
              private router: Router,
              private lenghtMessage: LenghtMessageService) {
    this.getAll(0);

  }

  ngOnInit(): void {
    this.messageService.currentMessage.subscribe(mes => {
      if (typeof mes != 'object') {
        window.scrollTo(1900, 800)
        this.search = mes;
        this.getAll(0);
      } else {
        window.scrollTo(0, 0)
      }

    })
  }

  getAll(size: number) {
    this.productService.getAll(this.search, size).subscribe(data => {
      if ((data as any).length == 0) {
        Swal.fire({
          position: 'center',
          icon: 'warning',
          title: 'Không tìm thấy',
          text: 'Sản phẩm bạn cần tìm không có',
          showConfirmButton: false,
          timer: 2000
        });
        this.messageService.changeMassege('')
      } else {
        this.productList = (data as any).content;
        this.last = (data as any).last;
        this.size = (data as any).size;
      }
    })
  }


  addToCart(productId: any) {
    this.cartService.addToCart(productId, this.tokenService.getId(), 1, 1).subscribe(data => {
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Đã thêm vào giỏ hàng!',
        showConfirmButton: false,
        timer: 3000
      });
      this.cartService.getAllCart(this.tokenService.getId()).subscribe(data => {
        this.lenghtMessage.changeMassege(data.length);
      })
    }, error => {
      if (error.error === 'errorLogin') {
        Swal.fire({
          position: 'center',
          icon: 'error',
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
      }else if (error.error==='errorQuantity'){
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
}
