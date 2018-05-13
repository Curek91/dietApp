import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../models/IProduct';

@Component({
  selector: 'app-product-on-diet',
  templateUrl: './product-on-diet.component.html',
  styleUrls: ['./product-on-diet.component.css']
})
export class ProductOnDietComponent implements OnInit {
  @Input() productInput: Product;

  weight: Number = 0;

  constructor() { }

  ngOnInit() {
  }

  getTooltipText(product: Product): String {
    return 'Białko: ' + product.protein * (this.productInput.weight / 100) + 'g\n' +
      'Węglowodany: ' + product.carb * (this.productInput.weight / 100) + 'g\n' +
      'Tłuszcze: ' + product.fat * (this.productInput.weight / 100) + 'g\n' +
      'Kalorie: ' + product.kcal * (this.productInput.weight / 100);
  }

  fakeAction(): void{
    console.log('Nic nie rob');
  }

}
