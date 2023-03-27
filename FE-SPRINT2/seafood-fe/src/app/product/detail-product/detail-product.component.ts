import { Component, OnInit } from '@angular/core';
import {ImageService} from "../../service/image.service";
import {SizeService} from "../../service/size.service";
import {ProductService} from "../../service/product.service";
import {Product} from "../../model/product";
import {ActivatedRoute} from "@angular/router";
import {Image} from "../../model/image";
import {Size} from "../../model/size";

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

  constructor(private imageService: ImageService,
              private sizeService: SizeService,
              private productService: ProductService ,
              private activatedRoute: ActivatedRoute,) {
    this.activatedRoute.paramMap.subscribe(next => {
      // @ts-ignore
      this.id = +next.get('id');
      this.getProduct(this.id);
      this.getImage(this.id)
      this.getSize(this.id)
    });
  }

  ngOnInit(): void {
    window.scrollTo(1900,660)
  }

  getProduct(productId: number){
    this.productService.findById(productId).subscribe(data=>{
      // @ts-ignore
      this.product = data;
      this.price = this.product.price;
      console.log(data)
    })
  }
  getSize(id: number){
    this.sizeService.getAllSize(id).subscribe(data=>{
      // @ts-ignore
      this.sizeList = data

    })
  }

  getImage(id: number){
    this.imageService.getAllSize(id).subscribe(data=>{
      // @ts-ignore
      this.imageList = data
      console.log(this.imageList)
    })
  }


  calculatePrice() {
    // @ts-ignore
    this.totalPrice = this.selectedSize * this.price;
    console.log(this.selectedSize)
    console.log(this.price)
    console.log(this.totalPrice)
  }
}
