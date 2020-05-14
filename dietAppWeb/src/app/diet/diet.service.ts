import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Product} from './models/Product';
import {ProductType} from './models/ProductType';
import 'rxjs/add/operator/map';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';
import {Diet} from "./models/Diet";
import {Client} from "../client/models/Client";

@Injectable()
export class DietService {

  private apiUrl: String = 'http://localhost:8091/';
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });
  private headersDeleteDiet = new HttpHeaders({
    'Content-Type': 'text',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });
  private headersImage = new HttpHeaders({
    'Content-Type': 'image/jpeg',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });
  constructor(private http: HttpClient, private authService: AuthService) {
  }


//////////////////////////////////////////////////////
// Diets /////////////////////////////////////////////
//////////////////////////////////////////////////////
  getDiet(id: number): Observable<Diet>{
    return this.http.get<Diet>(this.apiUrl + 'diets/' + id, {headers: this.headers});
  }

  addDiet(data, clientNo): Observable<Diet> {
    return this.http.post<Diet>(this.apiUrl + 'diets/' + clientNo, data, {headers: this.headers});
  }

  deleteDiet(id: number): any {
    return this.http.delete(this.apiUrl + 'diets/' + id, {headers: this.headers, responseType: 'text'});
  }

  modifyDiet(data, dietId): Observable<Diet> {
    return this.http.put<Diet>(this.apiUrl + 'diets/' + data.id, data, {headers: this.headers});
  }

//////////////////////////////////////////////////////
// Products //////////////////////////////////////////
//////////////////////////////////////////////////////
  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + 'products/' + id, {headers: this.headers});
  }

  getProductByName(name: string): Observable<Product> {
    return this.http.get<Product>(this.apiUrl + 'products/byName/' + name, {headers: this.headers});
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + 'products', {headers: this.headers});
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

//////////////////////////////////////////////////////
// Images ////////////////////////////////////////////
//////////////////////////////////////////////////////
  getImage(id: number): Observable<Blob> {
    return this.http.get(this.apiUrl + 'getImage/' + id, { responseType: 'blob', headers: this.headersImage });
  }

//////////////////////////////////////////////////////
// Types /////////////////////////////////////////////
//////////////////////////////////////////////////////
  getTypes(): Observable<ProductType[]> {
    return this.http.get<ProductType[]>(this.apiUrl + 'types', {headers: this.headers});
  }

}
