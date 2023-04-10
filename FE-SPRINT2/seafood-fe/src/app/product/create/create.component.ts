import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../../service/category.service";
import {OriginService} from "../../service/origin.service";
import {Origin} from "../../model/origin";
import {Category} from "../../model/category";
import {FormControl, FormGroup} from "@angular/forms";
import {LenghtMessageService} from "../../service/lenght-message.service";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  originList: Origin[] = [];
  categoryList: Category[] = [];
  formCreate: FormGroup;
  constructor(private categoryService: CategoryService,
              private originService: OriginService,
              private title: Title) {
    this.categoryService.getAll().subscribe(data=>{
      this.categoryList = data
    })
    this.originService.getAll().subscribe(data=>{
      this.originList = data
    })
    this.formCreate = new FormGroup({
      origin: new FormControl(),
      category: new FormControl(),
      name: new FormControl(),
      price: new FormControl(),
      image: new FormControl(),
    })
  }

  ngOnInit(): void {
    window.scrollTo(1900,700)
    this.title.setTitle('Thêm mới sản phẩm')
  }

  submit() {

  }
}
