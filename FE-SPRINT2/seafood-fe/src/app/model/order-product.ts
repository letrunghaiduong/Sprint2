import {User} from "./user";

export interface OrderProduct {
  id?: number
  orderDate?: string
  shippingAddress?: string
  code?: string
  user?: User
  totalPrice: number
}
