import { Component, inject, OnInit, signal } from '@angular/core';
import { HttpService } from '../../Services/http.service';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { INewResponse } from '../../Models/user-auth-model';
import { MatCardModule } from '@angular/material/card';
import { DatePipe } from '@angular/common';
import { HeaderComponent } from "../../Components/header/header.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatCardModule, DatePipe, HeaderComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export default class HomeComponent  implements OnInit {
  _http = inject(HttpService);
  news = signal<INewResponse|null>(null);
  

  ngOnInit(): void {
    this._http.GET<any[]>('/auth/role/get/all').subscribe(news => {
      console.log(news);
    });
    // this.getNews("2024-08-19","2024-08-20","popularity").subscribe({next:(value)=>{
    //   this.news.set(value);
    // }})
    
  }
    
  getNews(fromdate: string, todate: string, sortby: string): Observable<any> {
    const params = new HttpParams({
      fromObject: {
   
        fromdate,
        todate,
        sortby
      }
    });
    return this._http.GETParam<INewResponse>('/api/news', params);
  }

 

 
}
