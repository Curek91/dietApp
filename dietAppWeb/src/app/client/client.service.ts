import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';
import {Client} from './models/Client';
import {Observable} from 'rxjs/Rx';
import {Diet} from '../diet/models/Diet';
import {Page} from './models/Page';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private apiUrl: String = 'http://localhost:8080/dietApp-0.0.1-SNAPSHOT/';
  private headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  addClient(data): Observable<Client> {
    return this.http.post<Client>(this.apiUrl + 'clients', data, {headers: this.headers});
  }

  deleteClient(clientNo: number): any {
    return this.http.delete<any>(this.apiUrl + 'clients/' + clientNo, {headers: this.headers});
  }

  sendEmail(dietId): Observable<Diet> {
    return this.http.post<Diet>(this.apiUrl + 'api/sendEmail', dietId, {headers: this.headers});
  }

  createNewVersion(clientNo: number, data): Observable<Client> {
    return this.http.post<Client>(this.apiUrl + 'clients/createNewVersion/' + clientNo, data, {
      headers: this.headers,
    });
  }

  getClientVersions(clientNo: number): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl + 'clients/clientVersions/' + clientNo, {headers: this.headers});
  }

  getOhterPage(link: string, ) {
    return this.http.get<Page>(link, {headers: this.headers});
  }

  getClientsByName(name: string, sortBy: string = 'firstname', direction: string = 'asc', size: string = '5', page: string = '0') {
    const searchParams = new HttpParams()
      .append('sort', sortBy + ',' + direction)
      .append('page', page)
      .append('size', size)
      .append('name', name);
    return this.http.get<Page>(this.apiUrl + 'clientsNewestsByFirstnameOrLastname', {headers: this.headers, params: searchParams});
  }




  getClientDiets(clientNo: number): Observable<Diet[]> {
    return this.http.get<Diet[]>(this.apiUrl + 'diets/byClientNo/' + clientNo, {headers: this.headers});
  }

  getClientNewestVersion(clientNo: number): Observable<Client> {
    return this.http.get<Client>(this.apiUrl + 'clients/newestByClientNo/' + clientNo, {headers: this.headers});
  }

}
