import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth.service';
import {Router} from '@angular/router';
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: String = '';
  constructor(private keycloakService: KeycloakService, private router: Router) {
    this.username = '';
  }

  ngOnInit() {
    this.username = this.keycloakService.getUsername();
  }

  logout() {
    this.keycloakService.logout('http://localhost:4200/client');
  }
}
