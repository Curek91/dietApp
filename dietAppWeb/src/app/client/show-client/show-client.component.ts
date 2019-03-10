import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Client} from '../models/Client';

@Component({
  selector: 'app-show-client',
  templateUrl: './show-client.component.html',
  styleUrls: ['./show-client.component.css']
})
export class ShowClientComponent implements OnInit {

  clientForm: FormGroup;
  showInfo: boolean;
  showDiets: boolean;
  showTrainings: boolean;

  constructor(private formBuilder: FormBuilder,
              private clientService: ClientService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.showInfo = true;
    this.showDiets = false;
    this.showTrainings = false;
    this.clientForm = this.formBuilder.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      age: ['', Validators.required],
      weight: ['', Validators.required],
      height: ['', Validators.required],
      email: ['', Validators.required],
      telephone: ['', Validators.required]
    });
    this.loadClient();
  }

  parseFormToEntity(): Client {
    let client: Client;

    client = {
      id: +this.route.snapshot.params['id'],
      firstname: this.clientForm.value['firstname'].toString(),
      lastname: this.clientForm.value['lastname'].toString(),
      age: +this.clientForm.value['age'],
      weight: +this.clientForm.value['weight'],
      height: +this.clientForm.value['height'],
      email: this.clientForm.value['email'].toString(),
      telephone: this.clientForm.value['telephone'].toString()
    };
    return client;
  }

  modifyClient() {
    console.log(this.parseFormToEntity());
    this.clientService.modifyClient(this.parseFormToEntity()).subscribe(() => {
      console.log('modyfikuje klienta');

      this.router.navigate(['../manage-client']);
    });
  }

  loadClient() {
    const id = +this.route.snapshot.params['id'];
    this.clientService.getClient(id).subscribe((client) => {
      this.clientForm = this.formBuilder.group({
        firstname: [client.firstname, Validators.required],
        lastname: [client.lastname, Validators.required],
        age: [+client.age, Validators.required],
        weight: [+client.weight, Validators.required],
        height: [+client.height, Validators.required],
        email: [client.email, Validators.required],
        telephone: [client.telephone, Validators.required]
      });
    });
  }
}
