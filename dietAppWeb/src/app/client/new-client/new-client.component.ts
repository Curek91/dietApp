import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Client} from '../models/Client';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit {

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
      telephone: ['', Validators.required],
      biceps: [''],
      chest: [''],
      waist: [''],
      thigh: [''],
      modificationTime: ['']
    });
  }

  parseFormToEntity(): Client {
    let client: Client;

    client = {
      id: null,
      clientNo: null,
      firstname: this.clientForm.value['firstname'].toString(),
      lastname: this.clientForm.value['lastname'].toString(),
      age: +this.clientForm.value['age'],
      weight: +this.clientForm.value['weight'],
      height: +this.clientForm.value['height'],
      email: this.clientForm.value['email'].toString(),
      telephone: this.clientForm.value['telephone'].toString(),
      biceps: +this.clientForm.value['height'],
      chest: +this.clientForm.value['chest'],
      waist: +this.clientForm.value['waist'],
      thigh: +this.clientForm.value['thigh'],
      modificationTime: this.clientForm.value['modificationTime']
    };
    return client;
  }

  addClient() {
    console.log(this.parseFormToEntity());
    this.clientService.addClient(this.parseFormToEntity()).subscribe(() => {

      this.router.navigate(['../manage-client']);
    });
  }

}
