import {Component, ViewChild} from '@angular/core';
import {AuthService} from './auth/auth.service';
import {NavbarComponent} from './core-module/navbar/navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authService: AuthService) {
  }

  @ViewChild('appNavbar') navbar: NavbarComponent;

  ifLogged(): boolean {
    if (this.authService.getToken()) {
      return true;
    } else {
      return false;
    }
  }

}
