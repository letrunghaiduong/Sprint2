import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DetailProductComponent} from "./detail-product/detail-product.component";
import {ListProductComponent} from "./list-product/list-product.component";
import {AdminGuard} from "../security/admin.guard";
import {CreateComponent} from "./create/create.component";

const routes: Routes = [
  {path: 'detail/:id',component: DetailProductComponent},
  {path: 'list',canActivate:[AdminGuard],component: ListProductComponent},
  {path: 'create',canActivate:[AdminGuard],component: CreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
