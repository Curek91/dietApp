import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../models/Product';

@Component({
  selector: 'app-product-on-diet',
  templateUrl: './product-on-diet.component.html',
  styleUrls: ['./product-on-diet.component.css']
})
export class ProductOnDietComponent implements OnInit {
  @Input() productInput: Product;


  constructor() { }

  ngOnInit() {
  }

  getTooltipText(product: Product): String {
    return 'Białko: ' + product.protein  + 'g\n' +
      'Węglowodany: ' + product.carbs  + 'g\n' +
      'Tłuszcze: ' + product.fat  + 'g\n' +
      'Kalorie: ' + product.kcal;
  }


}
