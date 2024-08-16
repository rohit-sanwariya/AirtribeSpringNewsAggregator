import { AfterViewInit, ChangeDetectionStrategy, Component, model } from '@angular/core';
import {MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule } from '@angular/material/input';
import {MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatFormFieldModule,RouterLink,MatInputModule,MatButtonModule,FormsModule,],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection:ChangeDetectionStrategy.OnPush,
})
export default class LoginComponent  {
 
  username = model<string|null>(null);
  password = model<string|null>(null);
  onSubmit(loginForm:any) {
    console.log(this.username(),this.password());
    
  }

}
