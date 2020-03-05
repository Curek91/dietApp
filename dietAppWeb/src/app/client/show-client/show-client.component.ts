import {
  Component,
  OnInit,
  ViewChild
} from '@angular/core';
import {ClientService} from '../client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Client} from '../models/Client';
import {Diet} from '../../diet/models/Diet';
import {DietService} from '../../diet/diet.service';
import {NewDietComponent} from '../../diet/new-diet/new-diet.component';
import {ModalComponent, ModalModule} from 'angular-custom-modal';

@Component({
  selector: 'app-show-client',
  templateUrl: './show-client.component.html',
  styleUrls: ['./show-client.component.css']
})
export class ShowClientComponent implements OnInit {

  @ViewChild('newDietRef') newDietRef: NewDietComponent;
  @ViewChild('deleteDietModal') deleteDietModal: ModalComponent;

  clientForm: FormGroup;
  showInfo: boolean;
  showDiets: boolean;
  showTrainings: boolean;
  showProgress: boolean;
  showGraphs: boolean;
  showNewDiet: boolean;
  diets: Diet[] = new Array();

  dietIdToModify: number;

  clientVersions: Client[] = new Array();
  currentClientVersion: number = null;

  constructor(private formBuilder: FormBuilder,
              private clientService: ClientService,
              private dietService: DietService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.showInfo = true;
    this.showDiets = false;
    this.showProgress = false;
    this.showGraphs = false;
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
    this.loadClientVersions();
    this.loadClientDiets();
    this.clientForm.disable();
  }

  parseFormToEntity(): Client {
    let client: Client;

    client = {
      id: +this.route.snapshot.params['clientNo'],
      clientNo: null,
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

  loadClientDiets() {
    const id = +this.route.snapshot.params['clientNo'];
    let dietTemp: Diet;
    this.clientService.getClientDiets(id).subscribe((diets) => {
      this.diets = [];
      diets.forEach((diet) => {
        dietTemp = new Diet();
        dietTemp.id = diet.id;
        dietTemp.createdBy = diet.createdBy;
        dietTemp.creationTime = diet.creationTime;
        this.diets.push(dietTemp);
      });
    });
  }


  loadClient() {
    const id = +this.route.snapshot.params['clientNo'];
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

  loadClientVersions() {
    const clientNo = +this.route.snapshot.params['clientNo'];
    let clientTemp: Client;
    this.clientService.getClientVersions(clientNo).subscribe((clientVersions) => {
      this.clientVersions = [];

      clientVersions.forEach((client) => {

        clientTemp = {
          id: client.id,
          clientNo: client.clientNo,
          firstname: client.firstname,
          lastname: client.lastname,
          age: client.age,
          weight: client.weight,
          height: client.height,
          email: client.email,
          telephone: client.telephone
        };
        this.clientVersions.push(clientTemp);
      });
      console.log(this.clientVersions);
      this.clientVersions.sort((client1, client2) => client1.id - client2.id);
      this.currentClientVersion = this.clientVersions.length - 1;
      this.clientForm = this.formBuilder.group({
        firstname: [this.clientVersions[this.currentClientVersion].firstname, Validators.required],
        lastname: [this.clientVersions[this.currentClientVersion].lastname, Validators.required],
        age: [+this.clientVersions[this.currentClientVersion].age, Validators.required],
        weight: [+this.clientVersions[this.currentClientVersion].weight, Validators.required],
        height: [+this.clientVersions[this.currentClientVersion].height, Validators.required],
        email: [this.clientVersions[this.currentClientVersion].email, Validators.required],
        telephone: [this.clientVersions[this.currentClientVersion].telephone, Validators.required]
      });
    });
  }

  getPreviousClientVersion(): void {
    if (this.currentClientVersion > 0) {
      this.currentClientVersion = this.currentClientVersion - 1;
      this.clientForm = this.formBuilder.group({
        firstname: [this.clientVersions[this.currentClientVersion].firstname, Validators.required],
        lastname: [this.clientVersions[this.currentClientVersion].lastname, Validators.required],
        age: [+this.clientVersions[this.currentClientVersion].age, Validators.required],
        weight: [+this.clientVersions[this.currentClientVersion].weight, Validators.required],
        height: [+this.clientVersions[this.currentClientVersion].height, Validators.required],
        email: [this.clientVersions[this.currentClientVersion].email, Validators.required],
        telephone: [this.clientVersions[this.currentClientVersion].telephone, Validators.required]
      });
    }
  }

  getNextClientVersion(): void {
    if (this.currentClientVersion < this.clientVersions.length - 1) {
      this.currentClientVersion = this.currentClientVersion + 1;
      this.clientForm = this.formBuilder.group({
        firstname: [this.clientVersions[this.currentClientVersion].firstname, Validators.required],
        lastname: [this.clientVersions[this.currentClientVersion].lastname, Validators.required],
        age: [+this.clientVersions[this.currentClientVersion].age, Validators.required],
        weight: [+this.clientVersions[this.currentClientVersion].weight, Validators.required],
        height: [+this.clientVersions[this.currentClientVersion].height, Validators.required],
        email: [this.clientVersions[this.currentClientVersion].email, Validators.required],
        telephone: [this.clientVersions[this.currentClientVersion].telephone, Validators.required]
      });
    }
  }

  getFirstClientVersion(): void {
      this.currentClientVersion = 0;
      this.clientForm = this.formBuilder.group({
        firstname: [this.clientVersions[this.currentClientVersion].firstname, Validators.required],
        lastname: [this.clientVersions[this.currentClientVersion].lastname, Validators.required],
        age: [+this.clientVersions[this.currentClientVersion].age, Validators.required],
        weight: [+this.clientVersions[this.currentClientVersion].weight, Validators.required],
        height: [+this.clientVersions[this.currentClientVersion].height, Validators.required],
        email: [this.clientVersions[this.currentClientVersion].email, Validators.required],
        telephone: [this.clientVersions[this.currentClientVersion].telephone, Validators.required]
      });
  }

  getLastClientVersion(): void {
      this.currentClientVersion = this.clientVersions.length - 1;
      this.clientForm = this.formBuilder.group({
        firstname: [this.clientVersions[this.currentClientVersion].firstname, Validators.required],
        lastname: [this.clientVersions[this.currentClientVersion].lastname, Validators.required],
        age: [+this.clientVersions[this.currentClientVersion].age, Validators.required],
        weight: [+this.clientVersions[this.currentClientVersion].weight, Validators.required],
        height: [+this.clientVersions[this.currentClientVersion].height, Validators.required],
        email: [this.clientVersions[this.currentClientVersion].email, Validators.required],
        telephone: [this.clientVersions[this.currentClientVersion].telephone, Validators.required]
      });
  }

  deleteDiet(id: number): void {
    this.dietService.deleteDiet(id).subscribe((response) => {
      this.loadClientDiets();
    });
  }

  setDietId(dietId: number): void {
    this.dietIdToModify = dietId;
    this.newDietRef.getDiet();
  }

  createNewDiet(): void {
    this.dietIdToModify = null;
    this.showDiets = true;
    this.newDietRef.clearNewDiet();
  }

  sendEmail(dietId: number) {
    this.clientService.sendEmail(dietId).subscribe(() => {
      console.log('Wysyłam maila');
    });
  }
}
