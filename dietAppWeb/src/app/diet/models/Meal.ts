import {IMeal} from "./IMeal";
import {Product} from "./Product";
/**
 * Created by tomasz.cur on 14.04.2018.
 */
export class Meal implements IMeal {
  mealNo: number;
  products: Product[];
  kcal: number;
  suplements: string;

  constructor(mealNo: number) {
    this.products = [];
    this.mealNo = mealNo;
    this.kcal = 0;
    this.suplements = '';
  }
}
