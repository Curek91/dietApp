import {IDiet} from './IDiet';
import {Meal} from './Meal';
import {Client} from "../../client/models/Client";
/**
 * Created by tomasz.cur on 14.04.2018.
 */

export class Diet implements IDiet {
  id: number;
  meals: Meal[];
  kcal: number;
  clientNo: number;
  creationTime: Date;
  createdBy: string;

  constructor() {
    this.meals = [];
    this.kcal = 0;
    this.clientNo = null;
    this.createdBy = null;
    this.creationTime = null;
  }
}
