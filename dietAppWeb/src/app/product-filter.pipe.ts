import { Pipe, PipeTransform } from '@angular/core';
import {Product} from './diet/models/Product';

@Pipe({
  name: 'productFilter'
})
export class ProductFilterPipe implements PipeTransform {

  transform(items: Product[], searchText: string, searchType: string): any {

    console.log('searchText: ' + searchText);
    console.log('searchType: ' + searchType);
    if (!items) {
      return [];
    } else {
      if (!searchType) {
        if (!searchText) {
          return items;
        } else {
          return items.filter(it => {
            return it.name.toLowerCase().includes(searchText.toLowerCase());
          });
        }
      } else if (!searchText) {
        if (!searchType) {
          return items;
        } else {
          return items.filter(it => {
           return it.type.name.toLowerCase() === searchType.toLowerCase();
          });
        }
      } else {
        return items.filter(it => {
          return it.type.name.toLowerCase() === searchType.toLowerCase()
            && it.name.toLowerCase().includes(searchText.toLowerCase());
        });
      }
    }
  }

}
