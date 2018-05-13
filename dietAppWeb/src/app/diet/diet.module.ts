import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewDietComponent } from './new-diet/new-diet.component';
import { ProductComponent } from './product/product.component';
import { ProductOnDietComponent } from './product-on-diet/product-on-diet.component';
import {FormsModule} from "@angular/forms";
import {ProductFilterPipe} from "../product-filter.pipe";

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [NewDietComponent, ProductComponent],
  declarations: [NewDietComponent, ProductComponent, ProductOnDietComponent, ProductFilterPipe]
})
export class DietModule { }
