import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  API_USER = 'http://localhost:8080/api/user/'
  constructor(private httpClient: HttpClient) { }

  findById(userId: any){
    return  this.httpClient.get<User>(this.API_USER+'findById?userId=' + userId)
  }
}
