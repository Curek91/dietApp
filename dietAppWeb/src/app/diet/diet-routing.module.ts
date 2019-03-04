/**
 * Created by tomasz.cur on 28.07.2018.
 */
import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
import {ManageProductComponent} from './manage-product/manage-product.component';
import {NewProductComponent} from './new-product/new-product.component';
import {EditProductComponent} from './edit-product/edit-product.component';
import {CanActivateAuthGuard} from '../can-activate.authguard';

const DIET_ROUTES: Route[] = [
  {path: 'manage-products', component: ManageProductComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'new-product', component: NewProductComponent, canActivate: [CanActivateAuthGuard]},
  {path: 'product/:id', component: EditProductComponent, canActivate: [CanActivateAuthGuard]}
];

@NgModule({
  imports: [
    RouterModule.forChild(DIET_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})

export class DietRoutingModule {}
