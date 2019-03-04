/**
 * Created by tomasz.cur on 28.07.2018.
 */
import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
import {CanActivateAuthGuard} from '../can-activate.authguard';
import {ManageClientComponent} from './manage-client/manage-client.component';

const CLIENT_ROUTES: Route[] = [
  {path: 'manage-client', component: ManageClientComponent, canActivate: [CanActivateAuthGuard]}
];

@NgModule({
  imports: [
    RouterModule.forChild(CLIENT_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})

export class ClientRoutingModule {}
