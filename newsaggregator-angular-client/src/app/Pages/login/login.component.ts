import { ChangeDetectionStrategy, Component, inject, model } from '@angular/core';
import {MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule } from '@angular/material/input';
import {MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpService } from '../../Services/http.service';
import { AuthUserResponse } from '../../Models/user-auth-model';
 
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatFormFieldModule,RouterLink,MatInputModule,MatButtonModule,FormsModule,],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection:ChangeDetectionStrategy.OnPush,
})
export default class LoginComponent  {
  _http = inject(HttpService);
  _router = inject(Router);
  username = model<string|null>(null);
  password = model<string|null>(null);
  onSubmit() {
    this._http.POST<AuthUserResponse,{username:string|null,password:string|null}>('/auth/user/signin', {username:this.username(),password:  this.password()}).subscribe({
     next:(response)=>{
       if(response.data.token){
        localStorage.setItem('authLogin',JSON.stringify(response.data.token));
        this._router.navigateByUrl("/app");
       }
     }
    });
 }
}
