import {IMeal} from "./IMeal";
import {Product} from "./Product";
/**
 * Created by tomasz.cur on 14.04.2018.
 */
export class Meal implements IMeal {
  id: Number;
  products: Product[];
  kcal: number;
  suplements: string;

  constructor(id: Number) {
    this.products = [];
    this.id = id;
    this.kcal = 0;
    this.suplements = '';
  }
}
