import {Product} from "./product";
import {OrderProduct} from "./order-product";
import {User} from "./user";

export interface OrderDetail {
  id?: number
  product?: Product
  user?: User
}
