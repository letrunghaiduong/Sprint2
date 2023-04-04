import { Component, OnInit } from '@angular/core';
import {SecurityService} from "../../service/security.service";
import {TokenService} from "../../service/token.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import Swal from "sweetalert2";
import {Router} from "@angular/router";
import {MessageService} from "../../service/message.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  signInForm: FormGroup = new FormGroup({})
  checkLogIn = false;

  constructor(private securityService: SecurityService,
              private tokenService: TokenService,
              private formBuilder : FormBuilder,
              private router: Router,
              private messageService: MessageService) { }

  ngOnInit(): void {
    window.scrollTo(1900,700)
    this.getFormLogin()
  }

  getFormLogin(): void {
    this.signInForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(150)]],
    });
  }

  submit() {
  this.checkLogIn = true;
  const formLogin = this.signInForm.value;
  this.securityService.signIn(formLogin).subscribe(data=>{

    this.tokenService.setToken(data.token)
    this.tokenService.setId(data.id)
    this.tokenService.setAvatar(data.avatar)
    this.tokenService.setEmail(data.email)
    this.tokenService.setName(data.name)
    this.tokenService.setRole(data.roles)
    this.messageService.changeMassege(data);
    // location.href ='http://localhost:4200/'
    this.router.navigateByUrl('/')
  })
  }
}
