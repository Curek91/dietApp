import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Response } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/dietApp-0.0.1-SNAPSHOT/auth';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) {
  }
  login(username: string, password: string): Observable<boolean> {
    return this.http.post(this.authUrl, JSON.stringify({username: username, password: password}), {headers: this.headers})
      .map((response: Response) => {
        const token = response;
        if (response) {
          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));
          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      }).catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
  getToken(): String {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    const token = currentUser && currentUser.token;
    return token ? token['token'] : '';
  }
  logout(): void {
    // clear token remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }
  isLoggedIn(): boolean {
    const token: String = this.getToken();
    return token && token.length > 0;
  }
}
