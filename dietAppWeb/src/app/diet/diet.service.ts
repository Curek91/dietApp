import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Product} from './models/Product';
import {ProductType} from './models/ProductType';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DietService {

  private apiUrl: String = 'http://localhost:8091/';

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + 'products');
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + 'product/' + id);
  }

  getTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[]>(this.apiUrl + 'types');
  }

  addProduct(data): Observable<Product> {
    return this.http.post<Product>(this.apiUrl + 'product/create', data);
  }

  modifyProduct(data): Observable<Product> {
    return this.http.put<Product>(this.apiUrl + 'product/modify/' + data.id, data);
  }

  deleteProduct(id: number): any {
    return this.http.delete<any>(this.apiUrl + 'product/delete/' + id);
  }


}
