import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductType} from '../models/ProductType';
import {DietService} from '../diet.service';
import {Product} from '../models/Product';
import {ActivatedRoute, Router} from '@angular/router';
import {FileHolder} from 'angular2-image-upload';


@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {


  productForm: FormGroup;
  image: String;
  types: ProductType[] = new Array();
  constructor(private formBuilder: FormBuilder, private dietService: DietService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
     this.productForm = this.formBuilder.group({
       type: [null, Validators.required],
       name: ['', Validators.required],
       protein: ['', Validators.required],
       carbs: ['', Validators.required],
       fat: ['', Validators.required],
       kcal: ['', Validators.required]
     });
     this.loadProductTypes();
  }

  onUploadStateChanged(state: boolean) {
    console.log('asdasdas');
  }
  onRemoved(file: FileHolder) {
    console.log(file);
    console.log(this.image);
  }

  loadProductTypes(): void {
    let typeTemp: ProductType;
    this.dietService.getTypes().subscribe((types) => {
      console.log(types);
      types.forEach((type) => {

        typeTemp = {
          id: type.id,
          name: type.name,
        };
        this.types.push(typeTemp);
      });
      console.log('koiec');
      console.log(this.types);
    });
  }
  parseFormToEntity(): Product {
    let product: Product;

    product = {
      id: null,
      type: {id: +this.productForm.value['type'], name: null},
      name: this.productForm.value['name'].toString(),
      protein: +this.productForm.value['protein'],
      carbs: +this.productForm.value['carbs'],
      fat: +this.productForm.value['fat'],
      kcal: +this.productForm.value['kcal'],
      weight: null
    };
    return product;
  }

  addProduct() {
    console.log(this.parseFormToEntity());
    this.dietService.addProduct(this.parseFormToEntity()).subscribe((product) => {
      console.log('dodaje produkt');
      console.log('product.id: ' + product.id);

      this.router.navigate(['/product', product.id]);
    });
  }
}
