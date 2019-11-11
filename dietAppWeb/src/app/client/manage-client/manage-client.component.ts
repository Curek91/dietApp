import { Component, OnInit } from '@angular/core';
import {Client} from '../models/Client';
import {ClientService} from '../client.service';

@Component({
  selector: 'app-manage-client',
  templateUrl: './manage-client.component.html',
  styleUrls: ['./manage-client.component.css']
})
export class ManageClientComponent implements OnInit {

  clients: Client[] = new Array();
  filter: String = '';
  clientToDelete: number;

  constructor(private clientService: ClientService) {
  }

  ngOnInit() {
    this.loadClients();
  }

  loadClients(): void {
    let clientTemp: Client;
    this.clientService.getClients().subscribe((clients) => {
      this.clients = [];
      clients.forEach((client) => {

        clientTemp = {
          id: client.id,
          firstname: client.firstname,
          lastname: client.lastname,
          age: client.age,
          weight: client.weight,
          height: client.height,
          email: client.email,
          telephone: client.telephone
        };
         this.clients.push(clientTemp);
      });
      console.log(this.clients);
      this.clients.sort((client1, client2) => client1.id - client2.id);
    });
  }

  deleteClient(id: number): void {
    this.clientService.deleteClient(id).subscribe((response) => {
      this.loadClients();
    });
  }

  setClientToDelete(id: number): void{
    this.clientToDelete = id;
  }


}
