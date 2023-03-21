import {User} from "./user";

export interface OrderProduct {
  id?: number
  oderDate?: string
  shippingAddress?: string
  user?: User
  totalPrice: number
}
