import { Component, OnInit } from '@angular/core';
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {Title} from "@angular/platform-browser";
import {ImageService} from "../../service/image.service";
import {Image} from "../../model/image";

@Component({
  selector: 'app-list-product',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {
  productList: Product[] = []
  orderPage: any;
  page = 0;
  size =0;
  imageList: Image[] = [];
  constructor(private productService: ProductService,
              private title: Title,
              private imageService: ImageService) {
    this.getAll(this.page)
  }

  ngOnInit(): void {
    window.scrollTo(1900,700)
    this.title.setTitle('Danh sách sản phẩm')
  }

  getAll(page: number){
    this.page = page
    this.productService.showList(page).subscribe(data=>{
      this.productList = (data as any).content
      this.orderPage = data
      this.size = (data as any).size;
    })
  }


  getImage(id: any) {
    this.imageService.getAllImage(id).subscribe(data=>{
      // @ts-ignore
      this.imageList = data;
    })
  }
}
