import {Product} from './Product';
import {ProductToSend} from './ProductToSend';
/**
 * Created by tomasz.cur on 14.04.2018.
 */
export class MealToSend {
  mealNo: number;
  products: ProductToSend[];
  suplements: string;

  constructor(mealNo: number, suplements: string, products: ProductToSend[]) {
    this.products = products;
    this.mealNo = mealNo;
    this.suplements = suplements;
  }
}
