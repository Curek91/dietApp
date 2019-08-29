import { Component, OnInit } from '@angular/core';
import {Product} from '../models/Product';
import {DietService} from '../diet.service';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {

  products: Product[] = new Array();
  filter: String = '';

  constructor(private dietService: DietService) {
  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts(): void {
    let prodTemp: Product;
    this.dietService.getProducts().subscribe((products) => {
      this.products = [];
      products.forEach((product) => {

        prodTemp = {
          id: product.id,
          type: {
            id: product.type.id,
            name: product.type.name
          },
          name: product.name,
          protein: product.protein,
          carbs: product.carbs,
          fat: product.fat,
          kcal: product.kcal,
          weight: 0,
          sortNo: null
        };
        this.products.push(prodTemp);
      });
    });
  }

  deleteProduct(id: number): void {
    this.dietService.deleteProduct(id).subscribe((response) => {
      console.log('deleted');
      console.log('id produktu: ' + id);
      this.loadProducts();
    });
  }

}
