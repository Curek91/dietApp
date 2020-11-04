import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NewDietComponent} from './new-diet/new-diet.component';
import {ProductComponent} from './product/product.component';
import {ProductOnDietComponent} from './product-on-diet/product-on-diet.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ProductFilterPipe} from '../product-filter.pipe';
import {ManageProductComponent} from './manage-product/manage-product.component';
import {RouterModule} from '@angular/router';
import {NewProductComponent} from './new-product/new-product.component';
import {EditProductComponent} from './edit-product/edit-product.component';
import {CanActivateAuthGuard} from '../can-activate.authguard';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {AngularFileUploaderModule} from 'angular-file-uploader';
import {DndModule, DraggableComponent, DraggableHandleComponent} from 'ng2-dnd';
import {AppComponent} from '../app.component';
import {ModalModule} from 'angular-custom-modal';
import {ChartsModule} from 'ng2-charts';
import {
  MatButtonModule,
  MatCheckboxModule, MatIconModule,
  MatInputModule,
  MatOptionModule,
  MatSelectModule, MatSnackBarModule,
  MatTableModule, MatTooltipModule
} from '@angular/material';
import {ClientModule} from '../client/client.module';
import {ClientService} from "../client/client.service";
import {AngularDraggableModule} from "angular2-draggable";


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    AngularFontAwesomeModule,
    AngularFileUploaderModule,
    DndModule.forRoot(),
    ModalModule,
    ChartsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatInputModule,
    MatSelectModule,
    MatOptionModule,
    MatTableModule,
    MatSnackBarModule,
    MatTooltipModule,
    MatIconModule,
    AngularDraggableModule
  ],
  bootstrap: [AppComponent],
  providers: [CanActivateAuthGuard, ClientService],
  exports: [NewDietComponent, ProductComponent, ManageProductComponent],
  declarations: [NewDietComponent,
    ProductComponent,
    ProductOnDietComponent,
    ProductFilterPipe,
    ManageProductComponent,
    NewProductComponent,
    EditProductComponent
  ]
})
export class DietModule {
}
