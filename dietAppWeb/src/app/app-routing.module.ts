/**
 * Created by tomasz.cur on 28.07.2018.
 */
import {NgModule} from '@angular/core';
import {NewDietComponent} from './diet/new-diet/new-diet.component';
import {Route, RouterModule} from '@angular/router';
import {LoginComponent} from './auth/login/login.component';
import {CanActivateAuthGuard} from './can-activate.authguard';
import {ManageClientComponent} from './client/manage-client/manage-client.component';
import {ManageProductComponent} from "./diet/manage-product/manage-product.component";

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'login'},
  {path: 'login', component: LoginComponent},
  {path: 'diet', component: NewDietComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'client', component: ManageClientComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'manage-products', component: ManageProductComponent, canActivate: [CanActivateAuthGuard]}
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
