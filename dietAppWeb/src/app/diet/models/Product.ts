import {IProduct} from "./IProduct";
import {ProductType} from "./ProductType";
/**
 * Created by tomasz.cur on 15.04.2018.
 */

export class Product implements IProduct {
  id: number;
  type: ProductType;
  name: string;
  protein: number;
  carbs: number;
  fat: number;
  kcal: number;
  weight: number;
  sortNo: number;

  constructor(product: Product) {
    this.id = product.id;
    this.type = product.type;
    this.name = product.name;
    this.protein = product.protein;
    this.carbs = product.carbs;
    this.fat = product.fat;
    this.kcal = product.kcal;
    this.weight = product.weight;
    this.sortNo = product.sortNo;
  }

}
