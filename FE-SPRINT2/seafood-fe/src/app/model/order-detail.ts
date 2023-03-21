import {Product} from "./product";
import {OrderProduct} from "./order-product";

export interface OrderDetail {
  id?: number
  product?:Product
  orderProduct?: OrderProduct
}
