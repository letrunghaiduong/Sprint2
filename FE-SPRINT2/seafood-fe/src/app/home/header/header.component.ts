import { Component, OnInit } from '@angular/core';
import {TokenService} from "../../service/token.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false
  role: string[] = []
  name: string | null | undefined;

  constructor(private tokenService: TokenService) {
    if (this.tokenService.getToken()){
      this.role = this.tokenService.getRole()
      this.name = this.tokenService.getName()
      this.checkLogin = true
    }
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
        location.reload()
        }
      })
  }
}
