import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  API_USER = 'http://localhost:8080/api/user/'
  constructor(private httpClient: HttpClient) { }

  findById(userId: string | number | null){
    return  this.httpClient.get(this.API_USER+'findById?userId=' + userId)
  }
}
