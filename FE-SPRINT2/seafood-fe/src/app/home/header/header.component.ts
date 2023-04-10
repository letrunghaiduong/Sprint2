import { Component, OnInit} from '@angular/core';
import {TokenService} from "../../service/token.service";
import Swal from "sweetalert2";
import {MessageService} from "../../service/message.service";
import {Router} from "@angular/router";
import {CartService} from "../../service/cart.service";
import {LenghtMessageService} from "../../service/lenght-message.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false
  role: string[] = []
  name: string | null | undefined;
  image: any;
  length: any ;
  search:any;
  constructor(private tokenService: TokenService,
              private messageService: MessageService,
              private router: Router,
              private cartService: CartService,
              private lenghtMessage: LenghtMessageService) {
    this.messageService.currentMessage.subscribe(mes =>{
      if (this.tokenService.getToken()){
        this.role = this.tokenService.getRole()
        this.name = this.tokenService.getName()
        this.image = this.tokenService.getAvatar()
        this.checkLogin = true
        this.lenghtMessage.currentMessage.subscribe(data=>{
          this.cartService.getAllCart(this.tokenService.getId()).subscribe(data=>{
            this.length = data.length
          })
        })
      }
    })
  }


  ngOnInit(): void {

  }


  logout() {
    Swal.fire({
      title: 'Bạn muốn đăng xuất?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Có',
      cancelButtonText: 'Không'
    }).then((result) => {
      if (result.isConfirmed) {
        window.sessionStorage.clear()
        this.role = this.tokenService.getRole()
        this.name = this.tokenService.getName()
        this.checkLogin = false
        this.router.navigateByUrl('/')
        this.length = 0
        }
      })
  }


  onSubmit(search: any){
    this.messageService.changeMassege(search);
    this.search = ''
    this.router.navigateByUrl('/')
  }


  home() {
    this.router.navigateByUrl('/')
    window.scrollTo(0,0)

  }
}
