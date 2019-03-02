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
  loading = false;
  error = '';
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService) { }
  ngOnInit() {
    // reset login status
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.authService.logout();
  }
  login() {
    this.loading = true;
    this.authService.login(this.loginForm.value['username'], this.loginForm.value['password'])
      .subscribe(result => {
        this.router.navigate(['diet']);
      }, error => {
        this.loading = false;
        this.error = error;
      });
  }
}
