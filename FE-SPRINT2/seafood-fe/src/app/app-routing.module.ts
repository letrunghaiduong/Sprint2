import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path:'', loadChildren: () => import("./home/home-routing.module").then(module => module.HomeRoutingModule)},
  {path: 'login', loadChildren: () => import("./log-in/log-in.module").then(module => module.LogInModule)},
  {path: 'cart', loadChildren: () => import("./cart/cart.module").then(module => module.CartModule)},
  {path: 'product', loadChildren: () => import("./product/product.module").then(module => module.ProductModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
