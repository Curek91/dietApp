import {PageDetails} from './PageDetails';

export class Page {
  _embedded: any;
  _links: any;
  page: PageDetails;

  constructor(){
    this._embedded = null;
    this._links = null;
    this.page = new PageDetails();
  }
}
