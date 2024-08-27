import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  _router = inject(Router);
  ngOnInit(): void {
     window.addEventListener('storage', (event:StorageEvent) => {
        if(!localStorage.getItem('authLogin')){
          this._router.navigateByUrl('/login');
        }
     })

  }
   
}
