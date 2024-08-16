import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
 baseurl = 'http://localhost:8088';
  _http:HttpClient = inject(HttpClient);


  public GET<T>(url:string):Observable<T>{
    return this._http.get<T>(this.baseurl+ url);
  }
  
  POST<R,P>(url: string, value: P):Observable<R> {
    return this._http.post<R>(this.baseurl+ url, value);
  }

}
