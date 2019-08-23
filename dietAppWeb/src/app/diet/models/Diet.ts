import {IDiet} from './IDiet';
import {Meal} from './Meal';
import {Client} from "../../client/models/Client";
/**
 * Created by tomasz.cur on 14.04.2018.
 */

export class Diet implements IDiet {
  meals: Meal[];
  kcal: number;
  clientId: number;

  constructor() {
    this.meals = [];
    this.kcal = 0;
    this.clientId = null;
  }
}
