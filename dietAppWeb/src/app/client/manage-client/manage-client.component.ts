import {Component, OnInit, ViewChild} from '@angular/core';
import {Client} from '../models/Client';
import {ClientService} from '../client.service';
import {ModalComponent} from "angular-custom-modal";

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
    this.clientService.getNewestClients().subscribe((clients) => {
      this.clients = [];
      clients.forEach((client) => {

        clientTemp = {
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
        };
         this.clients.push(clientTemp);
      });
      console.log(this.clients);
      this.clients.sort((client1, client2) => client1.clientNo - client2.clientNo);
    });
  }

  deleteClient(clientNo: number): void {
    this.clientService.deleteClient(clientNo).subscribe((response) => {
      this.loadClients();
    });
  }
}
