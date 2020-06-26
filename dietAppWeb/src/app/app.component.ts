import {Component, ViewChild} from '@angular/core';
import {AuthService} from './auth/auth.service';
import {NavbarComponent} from './core-module/navbar/navbar.component';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor() {
  }
}
