import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewDietComponent } from './new-diet/new-diet.component';
import { ProductComponent } from './product/product.component';
import { ProductOnDietComponent } from './product-on-diet/product-on-diet.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProductFilterPipe} from '../product-filter.pipe';
import { ManageProductComponent } from './manage-product/manage-product.component';
import {RouterModule} from '@angular/router';
import { NewProductComponent } from './new-product/new-product.component';
import { EditProductComponent } from './edit-product/edit-product.component';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule
  ],
  exports: [NewDietComponent, ProductComponent, ManageProductComponent],
  declarations: [NewDietComponent, ProductComponent, ProductOnDietComponent, ProductFilterPipe, ManageProductComponent, NewProductComponent, EditProductComponent]
})
export class DietModule { }
