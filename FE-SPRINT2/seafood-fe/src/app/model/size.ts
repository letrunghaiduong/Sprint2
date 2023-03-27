import {Product} from "./product";

export interface Size {
  id?: number
  size?: number
  product?:Product
  quantity?: number
}
