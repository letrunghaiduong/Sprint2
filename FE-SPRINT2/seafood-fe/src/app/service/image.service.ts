import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Image} from "../model/image";

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  API_IMAGE = 'http://localhost:8080/api/image/'
  constructor(private httpClient: HttpClient) { }

  getAllImage(productId: number){
    return  this.httpClient.get<Image>(this.API_IMAGE + 'list?productId='+ productId);
  }

}
