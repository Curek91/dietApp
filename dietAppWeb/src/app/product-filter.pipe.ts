import { Pipe, PipeTransform } from '@angular/core';
import {Product} from './diet/models/Product';

@Pipe({
  name: 'productFilter'
})
export class ProductFilterPipe implements PipeTransform {

  transform(items: Product[], searchText: string): any {
    if (!items) {
      return [];
    } else if (!searchText) {
      return items;
    }



    return items.filter(it => {
      if (!searchText) {
        return true;
      } else {
        return it.name.toLowerCase().includes(searchText.toLowerCase());
      }
    });
  }

}
