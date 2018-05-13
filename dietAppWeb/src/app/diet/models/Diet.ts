import {IDiet} from './IDiet';
import {Meal} from './Meal';
/**
 * Created by tomasz.cur on 14.04.2018.
 */

export class Diet implements IDiet {
  meals: Meal[];
  kcal: number;

  constructor() {
    this.meals = [];
    this.kcal = 0;
  }
}
