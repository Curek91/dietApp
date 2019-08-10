import {IClient} from './IClient';
import {Diet} from "../../diet/models/Diet";
/**
 * Created by tomasz.cur on 04.03.2019.
 */


export class Client implements IClient {
  id: number;
  firstname: string;
  lastname: string;
  age: number;
  weight: number;
  height: number;
  email: string;
  telephone: string;

  constructor(client: Client) {
    this.id = client.id;
    this.firstname = client.firstname;
    this.lastname = client.lastname;
    this.age = client.age;
    this.weight = client.weight;
    this.height = client.height;
    this.email = client.email;
    this.telephone = client.telephone;
  }
}
