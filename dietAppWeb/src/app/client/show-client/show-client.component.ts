import {
  Component, HostListener,
  OnInit,
  ViewChild
} from '@angular/core';
import {ClientService} from '../client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Client} from '../models/Client';
import {Diet} from '../../diet/models/Diet';
import {DietService} from '../../diet/diet.service';
import {NewDietComponent} from '../../diet/new-diet/new-diet.component';
import {ModalComponent, ModalModule} from 'angular-custom-modal';
import {ChartDataSets, ChartOptions} from 'chart.js';
import {MatSlider, MatSnackBar} from '@angular/material';
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {Product} from "../../diet/models/Product";

@Component({
  selector: 'app-show-client',
  templateUrl: './show-client.component.html',
  styleUrls: ['./show-client.component.css']
})
export class ShowClientComponent implements OnInit {

  @ViewChild('newDietRef') newDietRef: NewDietComponent;
  @ViewChild('deleteDietModal') deleteDietModal: ModalComponent;
  @ViewChild('saveClientModal') saveClientModal: ModalComponent;

  leftBoxShow = false;
  rightBoxShow = false;

  productsList: Product[] = new Array();

  leftProductsForm = new FormControl();
  leftFilteredProducts: Observable<string[]>;
  leftProduct: Product;

  rightProductsForm = new FormControl();
  rightFilteredProducts: Observable<string[]>;
  rightProduct: Product;

  clientForm: FormGroup;
  diets: Diet[] = new Array();

  clientVersions: Client[] = new Array();
  currentClientVersion: number;

  public lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' }
  ];
  public lineChartOptions: (ChartOptions) = {
    responsive: true,
  };
  public lineChartLegend = true;
  public lineChartType = 'line';

  public lineChartLabels = [];

  constructor(private formBuilder: FormBuilder,
              private clientService: ClientService,
              private dietService: DietService,
              private router: Router,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar) { }

  ngOnInit() {
    this.loadProducts();

    this.leftFilteredProducts = this.leftProductsForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filterLeft(value))
    );

    this.rightFilteredProducts = this.rightProductsForm.valueChanges.pipe(
      startWith(''),
      map(value => this._filterRight(value))
    );

    this.clientForm = this.formBuilder.group({
      firstname: ['',  Validators.required],
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
      date: ['']
    });
    this.clientForm.disable();
    this.loadClientVersions();
    this.loadClientDiets();
  }

  private _filterLeft(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.productsList.map(product => product.name)
                            .filter(product => product.toLowerCase().indexOf(filterValue) === 0);
  }


  private _filterRight(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.productsList.map(product => product.name)
      .filter(product => product.toLowerCase().indexOf(filterValue) === 0);
  }

  @HostListener('document:keyup', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.key === 'ArrowRight') {
      this.changeClientVersion(this.currentClientVersion + 1);
    } else if (event.key === 'ArrowLeft'){
      this.changeClientVersion(this.currentClientVersion - 1);
    }
  }

  parseFormToEntity(): Client {
    let client: Client;

    client = {
      clientNo: null,
      firstname: this.clientForm.value['firstname'].toString(),
      lastname: this.clientForm.value['lastname'].toString(),
      age: +this.clientForm.value['age'],
      weight: +this.clientForm.value['weight'],
      height: +this.clientForm.value['height'],
      email: this.clientForm.value['email'].toString(),
      telephone: this.clientForm.value['telephone'].toString(),
      biceps: +this.clientForm.value['biceps'],
      chest: +this.clientForm.value['chest'],
      waist: +this.clientForm.value['waist'],
      thigh: +this.clientForm.value['thigh'],
      date: this.clientForm.value['date']
    };
    return client;
  }

  loadClientDiets() {
    const clientNo = +this.route.snapshot.params['clientNo'];
    let dietTemp: Diet;
    this.clientService.getClientDiets(clientNo).subscribe((diets) => {
      this.diets = [];
      diets.forEach((diet) => {
        dietTemp = new Diet();
        dietTemp.id = diet.id;
        dietTemp.createdBy = diet.createdBy;
        dietTemp.creationTime = diet.creationTime;
        dietTemp.kcal = diet.kcal;
        this.diets.push(dietTemp);
      });
    });
  }

  convertDateUsingTimezone(date: Date): Date {
    return new Date(Date.UTC(date.getFullYear(), date.getUTCMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds()));
  }

  loadClientVersions() {
    const clientNo = +this.route.snapshot.params['clientNo'];
    let clientTemp: Client;
    this.clientService.getClientVersions(clientNo).subscribe((clientVersions) => {
      this.clientVersions = [];
      clientVersions.forEach((client) => {
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
          date: this.convertDateUsingTimezone(new Date(client.date))
        };
        this.clientVersions.push(clientTemp);
      });
      this.clientVersions.sort((client1, client2) => new Date(client1.date).getTime() - new Date(client2.date).getTime());


      this.currentClientVersion = this.clientVersions.length - 1;
      this.fillForm(this.currentClientVersion);

      this.fillChartValues(this.clientVersions);
    });
  }

  fillChartValues(clients: Client[]) {
    this.lineChartData = [
      {data: clients.map((client) => client.biceps), label: 'Biceps'},
      {data: clients.map((client) => client.chest), label: 'Klatka'},
      {data: clients.map((client) => client.waist), label: 'Talia'},
      {data: clients.map((client) => client.thigh), label: 'Udo'},
      {data: clients.map((client) => client.weight), label: 'Waga'},
      {data: clients.map((client) => client.height), label: 'Wzrost'}
    ];
    this.lineChartLabels.length = 0;
    for (let i = 0; i < clients.length; i++) {
      this.lineChartLabels.push(this.convertDateUsingTimezone(new Date(clients[i].date)).toLocaleDateString());
    }
  }

  fillForm(version: number) {
    this.clientForm.get('firstname').setValue(this.clientVersions[version].firstname);
    this.clientForm.get('lastname').setValue(this.clientVersions[version].lastname);
    this.clientForm.get('age').setValue(this.clientVersions[version].age);
    this.clientForm.get('weight').setValue(this.clientVersions[version].weight);
    this.clientForm.get('height').setValue(this.clientVersions[version].height);
    this.clientForm.get('email').setValue(this.clientVersions[version].email);
    this.clientForm.get('telephone').setValue(this.clientVersions[version].telephone);
    this.clientForm.get('biceps').setValue(this.clientVersions[version].biceps);
    this.clientForm.get('chest').setValue(this.clientVersions[version].chest);
    this.clientForm.get('waist').setValue(this.clientVersions[version].waist);
    this.clientForm.get('thigh').setValue(this.clientVersions[version].thigh);
    this.clientForm.get('date').setValue(this.clientVersions[version].date);
  }

  changeClientVersion(version: number) {
    if (version >= 0 && version <= this.clientVersions.length - 1) {
      this.currentClientVersion = version;
      this.fillForm(this.currentClientVersion);
      this.clientForm.disable();
    }
  }

  deleteDiet(id: number): void {
    this.dietService.deleteDiet(id).subscribe((response) => {
      this.loadClientDiets();
    });
    if (id === this.newDietRef.dietId) {
      this.newDietRef.clearNewDiet();
    }
    this.deleteDietModal.close();
  }

  setDietId(dietId: number): void {
    this.newDietRef.dietId = dietId;
    this.newDietRef.diet.id = dietId;
    this.newDietRef.getDiet();
  }

  createNewDiet(): void {
    this.newDietRef.dietId = null;
    this.newDietRef.clearNewDiet();
    this.openSnackBar('Tworzysz nową dietę', 'Dieta');
  }

  sendEmail(dietId: number) {
    this.clientService.sendEmail(dietId).subscribe(() => {
      console.log('Wysyłam maila');
    });
  }

  createNewVersion() {
    console.log('Weszlo');
    this.currentClientVersion = this.clientVersions.length - 1;
    this.changeClientVersion(this.currentClientVersion);
    this.clientForm.enable();
  }

  cancelNewVersion() {
    this.changeClientVersion(this.clientVersions.length - 1);
    this.clientForm.disable();
  }

  commitNewVersion() {
    this.clientService.createNewVersion(+this.route.snapshot.params['clientNo'], this.parseFormToEntity()).subscribe(
      () => {
        console.log('Creating new version of client');
        this.loadClientVersions();
        this.clientForm.disable();
        this.saveClientModal.close();
        this.openSnackBar('Nowa wersja klienta została utworzona', 'Klient');
      }
    );
  }

  loadProducts(): void {
    let prodTemp: Product;
    this.dietService.getProducts().subscribe((products) => {
      products.forEach((product) => {

        prodTemp = {
          id: product.id,
          type: null,
          name: product.name,
          protein: null,
          carbs: null,
          fat: null,
          kcal: null,
          weight: 0,
          sortNo: null
        };
        this.productsList.push(prodTemp);
      });
    });
  }

  leftProductValueChange() {
    this.dietService.getProductByName(this.leftProductsForm.value).subscribe((product) => {
      this.leftProduct = new Product(product);
    });
  }

  rightProductValueChange() {
    this.dietService.getProductByName(this.rightProductsForm.value).subscribe((product) => {
      this.rightProduct = new Product(product);
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }
}


