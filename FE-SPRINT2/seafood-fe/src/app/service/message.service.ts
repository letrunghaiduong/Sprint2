import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private result = new BehaviorSubject<string>("");

  currentMessage = this.result.asObservable();

  constructor() { }

 changeMassege(message: any){
    this.result.next(message);
 }

}
