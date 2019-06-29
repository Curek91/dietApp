import {Product} from "./Product";
/**
 * Created by tomasz.cur on 11.04.2018.
 */
export interface IMeal {
  mealNo: number;
  products: Product[];
  kcal: number;
  suplements: String;
}
