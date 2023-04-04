import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListProductComponent} from "../product/list-product/list-product.component";
import {ProfileUserComponent} from "./profile-user/profile-user.component";

const routes: Routes = [
  {path: 'profile',component: ProfileUserComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
