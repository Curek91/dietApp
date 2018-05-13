import {IProduct} from "./IProduct";
/**
 * Created by tomasz.cur on 15.04.2018.
 */

export class Product implements IProduct {
  id: string;
  type: string;
  name: string;
  protein: number;
  carb: number;
  fat: number;
  kcal: number;
  imageName: string;
  weight: number;

  constructor(product: Product) {
    this.id = product.id;
    this.type = product.type;
    this.name = product.name;
    this.protein = product.protein;
    this.carb = product.carb;
    this.fat = product.fat;
    this.kcal = product.kcal;
    this.imageName = product.imageName;
    this.weight = 0;
  }
}
