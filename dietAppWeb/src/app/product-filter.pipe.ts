import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "./diet/models/Product";

@Pipe({
  name: 'productFilter'
})
export class ProductFilterPipe implements PipeTransform {

  transform(items: Product[], searchText: string, productType: string): Product[] {
    if (!items) {
      return [];
    }
    if (!searchText && productType.toLowerCase() === 'wszystko') {
      return items;
    }

    return items.filter(it => {
      if (!searchText) {
        console.log(it.type);
        console.log(productType);
        return it.type.toLowerCase() === productType.toLowerCase();
      } else if (productType.toLowerCase() === 'wszystko') {
        return it.name.toLowerCase().includes(searchText.toLowerCase());
      } else {
        return it.name.toLowerCase().includes(searchText.toLowerCase())
          && (it.type.toLowerCase() === productType.toLowerCase());
      }
    });
  }

}
