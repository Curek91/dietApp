import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Product} from './models/Product';
import {ProductType} from './models/ProductType';
import 'rxjs/add/operator/map';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';

@Injectable()
export class DietService {

  private apiUrl: String = 'http://localhost:8091/';
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });
  constructor(private http: HttpClient, private authService: AuthService) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + 'products', {headers: this.headers});
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + 'product/' + id, {headers: this.headers});
  }

  getTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[]>(this.apiUrl + 'types', {headers: this.headers});
  }

  addProduct(data): Observable<Product> {
    return this.http.post<Product>(this.apiUrl + 'product/create', data, {headers: this.headers});
  }

  modifyProduct(data): Observable<Product> {
    return this.http.put<Product>(this.apiUrl + 'product/modify/' + data.id, data, {headers: this.headers});
  }

  deleteProduct(id: number): any {
    return this.http.delete<any>(this.apiUrl + 'product/delete/' + id, {headers: this.headers});
  }
}
