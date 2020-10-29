import {Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {Client} from '../models/Client';
import {ClientService} from '../client.service';
import {Links} from '../models/Links';
import {Page} from '../models/Page';
import {Sort} from '../models/Sort';
import {ModalComponent} from "angular-custom-modal";

@Component({
  selector: 'app-manage-client',
  templateUrl: './manage-client.component.html',
  styleUrls: ['./manage-client.component.css']
})
export class ManageClientComponent implements OnInit {

  clients: Client[] = new Array();
  filter = '';
  page: Page = new Page();
  sort: Sort = new Sort();

  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.loadClients();
  }

  reloadClients(link: string): void {
    this.clientService.getOhterPage(link).subscribe((page) => {
      this.fillTable(page);
    });
  }

  loadClients(name: string = '', sortBy: string = 'firstname', direction: string = 'asc', size: string = '15', page: string = '0'): void {
    // tslint:disable-next-line:no-shadowed-variable
    this.clientService.getClientsByName(name, sortBy, direction, size, page).subscribe((page) => {
      this.fillTable(page);
    });
  }

  fillTable(page: any) {
    this.page = page;
    this.clients = [];
    if (page._embedded != null) {
      page._embedded.clientDTOList.forEach((client) => {
        this.clients.push(new Client({
          clientNo: client.clientNo,
          firstname: client.firstname,
          lastname: client.lastname,
          age: client.age,
          weight: client.weight,
          height: client.height,
          email: client.email,
          telephone: client.telephone,
          biceps: client.biceps,
          chest: client.chest,
          waist: client.waist,
          thigh: client.thigh,
          date: client.date
        }));
      });
    }
  }

  deleteClient(clientNo: number): void {
    this.clientService.deleteClient(clientNo).subscribe((response) => {
      this.loadClients(this.filter);
    });
  }

  @HostListener('document:keyup', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.key === 'ArrowRight') {
      this.reloadClients(this.page._links.next.href.toString());
    } else if (event.key === 'ArrowLeft') {
      this.reloadClients(this.page._links.prev.href.toString());
    }
  }

  valuechange() {
    this.loadClients(this.filter);
  }

  getLinkByNumber(x: number): string {
    const selfLink = this.page._links.self.href;
    return selfLink.replace(/page=\d+/gi, 'page=' + x);
  }

  getCurrentPage() {
    return this.page.page.number;
  }

  sorting(sortBy: string) {
    this.sort.sortBy = sortBy;
    if (this.sort.direction == null || this.sort.direction === 'desc' || this.sort.sortBy !== sortBy) {
      this.sort.direction = 'asc';
    } else {
      this.sort.direction = 'desc';
    }
    this.loadClients(this.filter, this.sort.sortBy, this.sort.direction);
  }

}
