import {Category} from "./category";
import {Origin} from "./origin";

export interface Product {
  id?: number
  name?: string
  category?: Category
  origin?: Origin
  price?: number
  image?: string
}
