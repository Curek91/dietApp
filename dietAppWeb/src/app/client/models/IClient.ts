import {Diet} from "../../diet/models/Diet";
export interface IClient {
  id: number;
  firstname: string;
  lastname: string;
  age: number;
  weight: number;
  height: number;
  email: string;
  telephone: string;
}
