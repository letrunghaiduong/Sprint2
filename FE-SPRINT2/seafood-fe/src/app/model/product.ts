import {Category} from "./category";
import {Origin} from "./origin";
import {Image} from "./image";
import {Size} from "./size";

export interface Product {
  id?: number
  name?: string
  category?: string
  origin?: string
  price?: number
  image?: string

}
