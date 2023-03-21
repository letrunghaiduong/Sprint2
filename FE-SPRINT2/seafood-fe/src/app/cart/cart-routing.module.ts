import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListCartComponent} from "./list-cart/list-cart.component";

const routes: Routes = [
  {path: '', component: ListCartComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CartRoutingModule { }
