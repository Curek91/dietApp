import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageClientComponent } from './manage-client/manage-client.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import { NewClientComponent } from './new-client/new-client.component';
import { ShowClientComponent } from './show-client/show-client.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {DietModule} from "../diet/diet.module";
import {ModalModule} from "angular-custom-modal";
import {ProductFilterPipe} from "../product-filter.pipe";
import {ClientFilterPipe} from "../client-filter.pipe";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    AngularFontAwesomeModule,
    DietModule,
    ModalModule
  ],
  declarations: [ManageClientComponent, NewClientComponent, ShowClientComponent, ClientFilterPipe]
})
export class ClientModule { }
