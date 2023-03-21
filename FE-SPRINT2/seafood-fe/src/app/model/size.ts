import {Product} from "./product";

export interface Size {
  id?: number
  size?: string
  product?:Product
  quantity?: number
}
