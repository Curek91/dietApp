import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {DietModule} from './diet/diet.module';
import {DietService} from './diet/diet.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ProductFilterPipe } from './product-filter.pipe';
import {CoreModuleModule} from './core-module/core-module.module';
import {RouterModule} from '@angular/router';
import {ManageProductComponent} from './diet/manage-product/manage-product.component';
import {NewDietComponent} from './diet/new-diet/new-diet.component';
import {AppRoutingModule} from './app-routing.module';
import {DietRoutingModule} from './diet/diet-routing.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    DietModule,
    HttpClientModule,
    FormsModule,
    CoreModuleModule,
    AppRoutingModule,
    DietRoutingModule
  ],
  providers: [DietService],
  bootstrap: [AppComponent]
})
export class AppModule { }
