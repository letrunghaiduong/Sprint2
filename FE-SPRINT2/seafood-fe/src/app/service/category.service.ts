import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Category} from "../model/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  API_CATEGORY = 'http://localhost:8080/api/category'

  constructor(private httpClient: HttpClient) { }
  getAll(){
    return  this.httpClient.get<Category[]>(this.API_CATEGORY)
  }
}
