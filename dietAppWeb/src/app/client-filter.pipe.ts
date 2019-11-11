import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "./diet/models/Product";
import {Client} from "./client/models/Client";

@Pipe({
  name: 'clientFilter'
})
export class ClientFilterPipe implements PipeTransform {

  transform(items: Client[], searchText: string): any {

    console.log('searchText: ' + searchText);
    if (!items) {
      return [];
    } else {
        return items.filter(it => {
          return it.lastname.toLowerCase().includes(searchText.toLowerCase()) || it.firstname.toLowerCase().includes(searchText.toLowerCase());
        });
      }
    }

}
