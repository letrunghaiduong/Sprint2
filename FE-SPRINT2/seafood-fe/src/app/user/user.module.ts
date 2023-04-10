import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { ProfileUserComponent } from './profile-user/profile-user.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [ProfileUserComponent],
    imports: [
        CommonModule,
        UserRoutingModule,
        ReactiveFormsModule
    ]
})
export class UserModule { }
