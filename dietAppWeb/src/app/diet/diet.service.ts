import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Product} from './models/Product';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class DietService {

  private apiUrl: string = 'http://demo2482307.mockable.io/getProducts';

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }
}
