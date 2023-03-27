import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Image} from "../model/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  API_SIZE = 'http://localhost:8080/api/image/'
  constructor(private httpClient: HttpClient) { }

  getAllSize(productId: number){
    return  this.httpClient.get<Image>(this.API_SIZE + 'list?productId='+ productId);
  }

}
