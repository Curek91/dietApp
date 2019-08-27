import {Meal} from './Meal';
import {Client} from "../../client/models/Client";
/**
 * Created by tomasz.cur on 11.04.2018.
 */
export interface IDiet {
  id: number;
  meals: Meal[];
  kcal: number;
  clientId: number;
  creationTime: Date;
  createdBy: string;
}
