import {Component, HostListener, OnInit} from '@angular/core';
import {Client} from '../models/Client';
import {ClientService} from '../client.service';
import {Links} from '../models/Links';
import {Page} from '../models/Page';

@Component({
  selector: 'app-manage-client',
  templateUrl: './manage-client.component.html',
  styleUrls: ['./manage-client.component.css']
})
export class ManageClientComponent implements OnInit {

  clients: Client[] = new Array();
  filter = '';
  links: Links;
  page: Page;
  private link: any;

  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.loadClients('');
  }

  reloadClients(link: string): void {
    this.clientService.getOhterPage(link).subscribe((page) => {
      this.fillTable(page);
    });
  }

  loadClients(name: string): void {
    this.clientService.getClientsByName(name).subscribe((page) => {
      this.fillTable(page);
    });
  }

  fillTable(page: any) {
    this.page = page;
    this.links = page._links;
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
    const regex = /^page=\d{1,}$/;
    console.log(regex.test('pasge=23'));

    if (event.key === 'ArrowRight') {
      this.reloadClients(this.links.next.href.toString());
    } else if (event.key === 'ArrowLeft'){
      this.reloadClients(this.links.prev.href.toString());
    }
  }

  valuechange($event: Event) {
      this.loadClients(this.filter);
  }

  getTotalPages(): number {
    return this.page.page.totalPages;
  }

  getLinkByNumber(x: number): string {
    const selfLink = this.page._links.self.href;
    return selfLink.replace(/page=\d+/gi, 'page=' + x);
  }

  getCurrentPage() {
    return this.page.page.number;
  }
}
