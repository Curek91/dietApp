import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ManageClientComponent } from './manage-client/manage-client.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import { NewClientComponent } from './new-client/new-client.component';
import { ShowClientComponent } from './show-client/show-client.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule
  ],
  declarations: [ManageClientComponent, NewClientComponent, ShowClientComponent]
})
export class ClientModule { }
