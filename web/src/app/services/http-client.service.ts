import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Order, PagenatedDetails} from '../models/Orders';
import {Users} from '../models/Users';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private baseUrl = 'http://localhost:8090/ecom-od/api';

  constructor(private http: HttpClient) {
  }

  getOrders(pageIndex: number, size: number): Observable<PagenatedDetails<Order>> {
    return  this.http.get<PagenatedDetails<Order>>(`${this.baseUrl}/orders/user?pageIndex=${pageIndex}&size=${size}`);
  }

  getUsers() {
    return this.http.get<Users[]>(`${this.baseUrl}/users`);
  }

  postUsers(user: Users) {
    return this.http.post(`${this.baseUrl}/orders`, [user]);
  }
}

