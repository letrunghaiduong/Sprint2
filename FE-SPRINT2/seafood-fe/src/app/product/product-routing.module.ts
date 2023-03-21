import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DetailProductComponent} from "./detail-product/detail-product.component";
import {ListProductComponent} from "./list-product/list-product.component";

const routes: Routes = [
  {path: 'detail',component: DetailProductComponent},
  {path: 'list',component: ListProductComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
