/**
 * Created by tomasz.cur on 28.07.2018.
 */
import {NgModule} from '@angular/core';
import {NewDietComponent} from './diet/new-diet/new-diet.component';
import {Route, RouterModule} from '@angular/router';
import {LoginComponent} from './auth/login/login.component';

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'login'},
  {path: 'diet', component: NewDietComponent},
  {path: 'login', component: LoginComponent},
  {path: 'client', component: NewDietComponent},
  {path: 'training', component: NewDietComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule {}
