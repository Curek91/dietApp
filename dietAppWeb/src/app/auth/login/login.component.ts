import { Component, OnInit } from '@angular/core';
import {AuthService} from '../auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  error = '';
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService) {
  }
  ngOnInit() {
    // reset login status
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['client']);
    }
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  login() {
    this.authService.login(this.loginForm.value['username'], this.loginForm.value['password'])
      .subscribe(result => {
        this.router.navigate(['client']);
      }, error => {
        this.error = error;
      });
  }
}
