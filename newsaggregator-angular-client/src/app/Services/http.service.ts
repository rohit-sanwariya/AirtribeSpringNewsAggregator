import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpService {
 
 baseurl = 'http://localhost:8088';
  _http:HttpClient = inject(HttpClient);


  public GET<T>(url:string):Observable<T>{
    const token = this.getToken();
    const headers = this.getAuthHeader(token);
    return this._http.get<T>(this.baseurl+ url,{headers:headers});
  }

  public GETParam<T>(url:string,params:HttpParams):Observable<T>{
    const token = this.getToken();
    const headers = this.getAuthHeader(token);
    return this._http.get<T>(this.baseurl+ url,{params:params,headers});
  }
  
  private getToken() {
    return JSON.parse(localStorage.getItem('authLogin')!).token;
  }

  private getAuthHeader(token: any) {
    return new HttpHeaders(
      {
        'Content-Type': 'application/json',
        "Authorization": "Bearer " + token
      }

    );
  }

  POST<R,P>(url: string, value: P):Observable<R> {
    return this._http.post<R>(this.baseurl+ url, value,{headers:this.getAuthHeader(this.getToken())});
  }
  PUT<R,P>(url: string, value: P):Observable<R> {
    return this._http.put<R>(this.baseurl+ url, value,{headers:this.getAuthHeader(this.getToken())});
  }
  POSTUnautorized<R,P>(url: string, value: P):Observable<R> {
    return this._http.post<R>(this.baseurl+ url, value);
  }

}
