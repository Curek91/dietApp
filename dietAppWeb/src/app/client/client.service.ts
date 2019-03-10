import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';
import {Client} from './models/Client';
import {Observable} from 'rxjs/Rx';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl: String = 'http://localhost:8091/';
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authService.getToken()
  });
  constructor(private http: HttpClient, private authService: AuthService) {
  }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl + 'clients', {headers: this.headers});
  }

  getClient(id: number): Observable<Client> {
    return this.http.get<Client>(this.apiUrl + 'client/' + id, {headers: this.headers});
  }

  addClient(data): Observable<Client> {
    return this.http.post<Client>(this.apiUrl + 'client/create', data, {headers: this.headers});
  }

  deleteClient(id: number): any {
    return this.http.delete<any>(this.apiUrl + 'client/delete/' + id, {headers: this.headers});
  }

  modifyClient(data): Observable<Client> {
    return this.http.put<Client>(this.apiUrl + 'client/modify/' + data.id, data, {headers: this.headers});
  }
}
