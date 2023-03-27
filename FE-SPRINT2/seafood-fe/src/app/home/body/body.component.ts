import {Component, Input, OnInit} from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import Swal from "sweetalert2";
import {TokenService} from "../../service/token.service";
import {MessageService} from "../../service/message.service";

@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})
export class BodyComponent implements OnInit {
  search: string = ''
  productList:Product[] = []
  last: any;
  first: any;
  size = 0;
  constructor(private productService: ProductService,
              private messageService: MessageService) {
    this.getAll(0);
  }

  ngOnInit(): void {
    this.messageService.currentMessage.subscribe(mes =>{
      window.scrollTo(1900,800)
      this.search = mes;
      this.getAll(0);
    })
  }

  getAll(page: number){
    this.productService.getAll(this.search,page).subscribe(data=>{
      // @ts-ignore
      if (data['content'].length == 0) {
        Swal.fire({
          position: 'center',
          icon: 'warning',
          title: 'Không tìm thấy',
          text: 'Kết quả bạn cần tìm là: ' + '" ' + this.search + ' " ' + ' không có',
          showConfirmButton: false,
          timer: 2000
        });
      } else {
        // @ts-ignore
        this.productList = data['content'];
        // @ts-ignore
        this.last = data['last'];
      }
    })
  }

  scrollProduct() {

  }
}
