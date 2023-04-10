import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminGuard} from "./security/admin.guard";
import {ErrorComponent} from "./error/error.component";

const routes: Routes = [
  {path:'', loadChildren: () => import("./home/home-routing.module").then(module => module.HomeRoutingModule)},
  {path: 'login', loadChildren: () => import("./log-in/log-in.module").then(module => module.LogInModule)},
  {path: 'cart', loadChildren: () => import("./cart/cart.module").then(module => module.CartModule)},
  {path: 'product', loadChildren: () => import("./product/product.module").then(module => module.ProductModule)},
  {path: 'user', loadChildren: () => import("./user/user.module").then(module => module.UserModule)},
  {path: 'salesReport',canActivate:[AdminGuard], loadChildren: () => import("./sales-report/sales-report.module").then(module => module.SalesReportModule)},
  {path: 'error',component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
