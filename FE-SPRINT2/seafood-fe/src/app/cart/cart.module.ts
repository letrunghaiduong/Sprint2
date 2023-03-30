import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { ListCartComponent } from './list-cart/list-cart.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [ListCartComponent],
    imports: [
        CommonModule,
        CartRoutingModule,
        FormsModule
    ]
})
export class CartModule { }
