import {IClient} from './IClient';
/**
 * Created by tomasz.cur on 04.03.2019.
 */


export class Client implements IClient {
  id: number;
  clientNo: number
  firstname: string;
  lastname: string;
  age: number;
  weight: number;
  height: number;
  email: string;
  telephone: string;
  biceps: number;
  chest: number;
  waist: number;
  thigh: number;

  constructor(client: Client) {
    this.id = client.id;
    this.clientNo = client.clientNo;
    this.firstname = client.firstname;
    this.lastname = client.lastname;
    this.age = client.age;
    this.weight = client.weight;
    this.height = client.height;
    this.email = client.email;
    this.telephone = client.telephone;
    this.biceps = client.biceps;
    this.chest = client.chest;
    this.waist = client.waist;
    this.thigh = client.thigh;
  }
}
