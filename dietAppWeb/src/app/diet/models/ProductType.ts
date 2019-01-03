
import {IProductType} from './IProductType';
/**
 * Created by tomasz.cur on 15.04.2018.
 */

export class ProductType implements IProductType {
  id: number;
  name: string;

  constructor(productType: ProductType) {
    this.id = productType.id;
    this.name = productType.name;
  }
}
