import { Component, inject, OnInit } from '@angular/core';
import { HttpService } from '../../Services/http.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export default class HomeComponent  implements OnInit {
  _http = inject(HttpService);

  ngOnInit(): void {
    this._http.GET<any[]>('/auth/role/get/all').subscribe(news => {
      console.log(news);
    });
  }
    
}
