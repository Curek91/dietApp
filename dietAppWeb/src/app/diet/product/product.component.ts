import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/Product";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() productInput: Product;

  constructor() { }

  ngOnInit() {
  }

  getTooltipText(product: Product): String {
    return 'Białko: ' + product.protein + 'g\n' +
           'Węglowodany: ' + product.carb + 'g\n' +
           'Tłuszcze: ' + product.fat + 'g\n' +
           'Kalorie: ' + product.kcal;
  }

}
