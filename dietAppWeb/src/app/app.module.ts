import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';


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
import {AuthModule} from './auth/auth.module';
import {AuthService} from './auth/auth.service';
import {CanActivateAuthGuard} from './can-activate.authguard';
import {ClientModule} from './client/client.module';
import {ClientRoutingModule} from './client/client-routing.module';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {KeycloakAngularModule, KeycloakService} from 'keycloak-angular';
import {initializer} from './app-init';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    DietModule,
    ClientModule,
    HttpClientModule,
    FormsModule,
    CoreModuleModule,
    AppRoutingModule,
    DietRoutingModule,
    ClientRoutingModule,
    AuthModule,
    AngularFontAwesomeModule,
    KeycloakAngularModule
  ],
  providers: [
    DietService,
    AuthService,
    CanActivateAuthGuard,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    }],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
