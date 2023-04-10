import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { DetailProductComponent } from './detail-product/detail-product.component';
import { ListProductComponent } from './list-product/list-product.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { CreateComponent } from './create/create.component';


@NgModule({
  declarations: [DetailProductComponent, ListProductComponent, CreateComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ProductModule { }
