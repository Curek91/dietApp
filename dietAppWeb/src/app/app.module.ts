import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {DietModule} from "./diet/diet.module";
import {DietService} from "./diet/diet.service";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { ProductFilterPipe } from './product-filter.pipe';
import {CoreModuleModule} from "./core-module/core-module.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    DietModule,
    HttpClientModule,
    FormsModule,
    CoreModuleModule
  ],
  providers: [DietService],
  bootstrap: [AppComponent]
})
export class AppModule { }
