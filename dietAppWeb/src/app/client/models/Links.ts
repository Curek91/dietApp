import {Link} from './Link';


export class Links {
  first: Link;
  prev: Link;
  self: Link;
  next: Link;
  last: Link;

  constructor(){
  this.first = new Link();
  this.prev = new Link();
  this.self = new Link();
  this.next = new Link();
  this.last = new Link();
  }
}
