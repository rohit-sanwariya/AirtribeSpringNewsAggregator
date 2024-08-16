import { NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject, model, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterLink } from '@angular/router';
import { HttpService } from '../../Services/http.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [MatFormFieldModule,NgIf,RouterLink,MatInputModule,MatButtonModule,ReactiveFormsModule,],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
  changeDetection:ChangeDetectionStrategy.OnPush,
})
export default class 
SignUpComponent implements OnInit {
  registerForm!: FormGroup;
  _http:HttpService = inject(HttpService);
  ngOnInit(): void {
    this.registerForm = new FormGroup({
      firstname: new FormControl('', [Validators.required]),
      lastname: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(15)])
    });
  } 
 

  onSubmit() {
     this._http.POST('/auth/user/register', this.registerForm.value).subscribe({
      next:(data)=>{
        console.log(data);
      }
     });
  }
  get firstname():FormControl {
    return this.registerForm.get('firstname') as FormControl;
  }

  get lastname():FormControl {
    return this.registerForm.get('lastname') as FormControl;
  }

  get email():FormControl {
    return this.registerForm.get('email') as FormControl;
  }

  get password():FormControl {
    return this.registerForm.get('password') as FormControl;
  }
}
