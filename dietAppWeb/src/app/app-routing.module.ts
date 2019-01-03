/**
 * Created by tomasz.cur on 28.07.2018.
 */
import {NgModule} from '@angular/core';
import {NewDietComponent} from './diet/new-diet/new-diet.component';
import {Route, RouterModule} from '@angular/router';

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'diet'},
  {path: 'diet', component: NewDietComponent}
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
