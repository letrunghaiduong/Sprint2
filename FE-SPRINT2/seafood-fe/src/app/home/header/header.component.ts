import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {TokenService} from "../../service/token.service";
import Swal from "sweetalert2";
import {MessageService} from "../../service/message.service";
import {FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {CartService} from "../../service/cart.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false
  role: string[] = []
  name: string | null | undefined;
  length: any ;
  constructor(private tokenService: TokenService,
              private messageService: MessageService,
              private router: Router,
              private cartService: CartService) {
    this.messageService.currentMessage.subscribe(mes =>{
      if (this.tokenService.getToken()){
        this.role = this.tokenService.getRole()
        this.name = this.tokenService.getName()
        this.checkLogin = true
      }
    })


  }

  ngOnInit(): void {
    this.cartService.getAllCart(this.tokenService.getId()).subscribe(data=>{
      this.length = data.length
    })
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
        }
      })
  }

  onSubmit(search: string){
    this.messageService.changeMassege(search);
    this.router.navigateByUrl('/')
    console.log(search)
  }

  home() {
    this.router.navigateByUrl('/')
  }
}
