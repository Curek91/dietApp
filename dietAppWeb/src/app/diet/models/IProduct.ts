import {ProductType} from "./ProductType";
/**
 * Created by tomasz.cur on 11.04.2018.
 */
export interface IProduct {
  id: number;
  type: ProductType;
  name: string;
  protein: number;
  carbs: number;
  fat: number;
  kcal: number;
  weight: number;
  imageName: string;
}
