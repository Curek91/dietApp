import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Product} from '../models/Product';
import {Diet} from '../models/Diet';
import {DietService} from '../diet.service';
import {Meal} from '../models/Meal';
import {ProductType} from '../models/ProductType';
import {IProduct} from '../models/IProduct';
import {ClientService} from '../../client/client.service';
import {ModalComponent} from 'angular-custom-modal';
import {DietToSend} from '../models/DietToSend';
import {MealToSend} from '../models/MealToSend';
import {ProductToSend} from '../models/ProductToSend';
import {ChartDataSets, MultiDataSet, Label, ChartType, ChartOptions, Chart} from 'chart.js';
import 'chartjs-plugin-datalabels';
import {MatSnackBar} from '@angular/material';
import * as xlsx from 'xlsx';

@Component({
  selector: 'app-new-diet',
  templateUrl: './new-diet.component.html',
  styleUrls: ['./new-diet.component.css']
})
export class NewDietComponent implements OnInit {

  @Input() clientNo: number;
  @Input() dietId: number;
  @Input() oldDiets: Diet[];

  @Output() refreshDietsEvent: EventEmitter<number> = new EventEmitter<number>();

  @ViewChild('createDietModal') createDietModal: ModalComponent;
  @ViewChild('updateDietModal') updateDietModal: ModalComponent;

  @ViewChild('summaryMealModal') summaryMealModal: ModalComponent;

  diet: Diet = new Diet();
  products: Product[] = new Array();
  activeMeal: number;
  filter: String = '';
  productTypeSelected: String = null;
  types: ProductType[] = new Array();
  oldDiet: Diet;
  dietView: string;
  productsView: string;
  doughnutChartType: ChartType = 'pie';

  constructor(private dietService: DietService, private _snackBar: MatSnackBar, private clientService: ClientService) {
  }

  ngOnInit() {

    this.dietView = 'list';
    this.productsView = 'list';
    this.loadProducts();
    this.loadProductTypes();
  }

  changeDietView(view: string) {
    this.dietView = view;
  }

  changeProductsView(view: string) {
    this.productsView = view;
  }

  loadProductTypes(): void {
    let typeTemp: ProductType;
    this.dietService.getTypes().subscribe((types) => {
      types.forEach((type) => {

        typeTemp = {
          id: type.id,
          name: type.name,
        };
        this.types.push(typeTemp);
      });
    });
  }

  loadProducts(): void {
    let prodTemp: Product;
    this.dietService.getProducts().subscribe((products) => {
      products.forEach((product) => {

        prodTemp = {
          id: product.id,
          type: {
            id: product.type.id,
            name: product.type.name
          },
          name: product.name,
          protein: product.protein,
          carbs: product.carbs,
          fat: product.fat,
          kcal: product.kcal,
          weight: 0,
          sortNo: null
        };
        this.products.push(prodTemp);
      });
    });
  }

  addToMeal(product: Product): void {
    const prod: Product = new Product(product);
    prod.weight = null;
    this.diet.meals[this.activeMeal - 1].products.push(prod);
    this.openSnackBar('Produkt ' + prod.name + ' został dodany do posiłku: ' + this.activeMeal, 'Proudkt dodany');
  }

  moveToMeal($event: any, id: number) {
    const prod: Product = new Product($event.dragData);
    this.diet.meals[id - 1].products.push(prod);
    this.openSnackBar('Produkt ' + prod.name + ' został dodany do posiłku: ' + id, 'Proudkt dodany');
  }

  addMeal(): void {
    if (this.activeMeal == null) {
      this.diet.meals.push(new Meal(1));
      this.activeMeal = 1;
    } else {
      for (const x of this.diet.meals) {
        if (x.mealNo > this.activeMeal) {
          x.mealNo += 1;
        }
      }
      this.diet.meals.push(new Meal(this.activeMeal + 1));
      this.activeMeal = this.activeMeal + 1;
      this.diet.meals.sort((n1, n2) => (n1.mealNo - n2.mealNo));
    }
    this.openSnackBar('Dodano posiłek numer ' + this.activeMeal, 'Posiłek');
  }

  deleteFromMeal(index: number): void {
    this.diet.meals[this.activeMeal - 1].products.splice(index, 1);
  }

  deleteMeal(): void {
    const active = this.activeMeal;
    this.diet.meals.map(function (item) {
      if (item.mealNo > active) {
        item.mealNo -= 1;
      }
    });
    this.diet.meals.splice(this.diet.meals.findIndex(function (element) {
      return element.mealNo === active;
    }), 1);
    if (active === this.diet.meals.length + 1) {
      this.activeMeal -= 1;
    }
    this.openSnackBar('Usunięto posiłek numer ' + active, 'Posiłek');
  }

  setActiveMeal(id: number) {
    this.activeMeal = id;
  }

  addDiet() {
    const dietToSend = new DietToSend();
    dietToSend.meals = this.diet.meals
      .map((m) => new MealToSend(m.mealNo, m.suplements, m.products
        .map(p => new ProductToSend(p.id, p.weight))));
    this.dietService.addDiet(dietToSend, this.clientNo).subscribe((diet) => {
      this.dietId = diet.id;
      this.diet.id = diet.id;
      this.diet.createdBy = diet.createdBy;
      this.diet.creationTime = diet.creationTime;
      this.refreshDietsEvent.next(this.clientNo);
    });
    this.createDietModal.close();
  }

  clearNewDiet(): void {
    this.dietId = null;
    this.diet = new Diet();
    this.activeMeal = 0;
  }

  getDiet(): void {
    this.dietService.getDiet(this.dietId).subscribe((diet) => {
        diet.meals.sort((leftSide, rightSide): number => {
          if (leftSide.mealNo < rightSide.mealNo) {
            return -1;
          }
          if (leftSide.mealNo > rightSide.mealNo) {
            return 1;
          }
          return 0;
        });
        diet.meals.forEach((meal) => {
          meal.products.sort((leftSide, rightSide): number => {
            if (leftSide.sortNo < rightSide.sortNo) {
              return -1;
            }
            if (leftSide.sortNo > rightSide.sortNo) {
              return 1;
            }
            return 0;
          });
        });
        this.diet = diet;
        this.activeMeal = 1;
      }
    );
  }

  modifyDiet() {
    this.diet.id = this.dietId;
    this.dietService.modifyDiet(this.diet, this.diet.id).subscribe(() => {
      this.refreshDietsEvent.next(this.clientNo);
    });
    this.updateDietModal.close();
  }

  getOldDiet(value): void {

    if (!value) {
      this.oldDiet = null;
      return;
    }

    this.dietService.getDiet(value).subscribe((diet) => {
        diet.meals.sort((leftSide, rightSide): number => {
          if (leftSide.mealNo < rightSide.mealNo) {
            return -1;
          }
          if (leftSide.mealNo > rightSide.mealNo) {
            return 1;
          }
          return 0;
        });
        diet.meals.forEach((meal) => {
          meal.products.sort((leftSide, rightSide): number => {
            if (leftSide.sortNo < rightSide.sortNo) {
              return -1;
            }
            if (leftSide.sortNo > rightSide.sortNo) {
              return 1;
            }
            return 0;
          });
        });
        this.oldDiet = diet;
      }
    );
  }

  getMealOnOldDiet(mealNo: number): Meal {
    let meal: Meal;
    if (this.oldDiet) {
      // tslint:disable-next-line:no-shadowed-variable triple-equals
      meal = this.oldDiet.meals.find(meal => meal.mealNo == mealNo);
      return meal;
    } else {
      return null;
    }
  }


  getValuesForMeal(meal: Meal, whatINeed: string): number {
    if (meal == null) {
      return null;
    }
    if (meal.products.length > 0) {
      if (whatINeed === 'kcal') {
        return Math.round(meal.products
          .map((product) => +product.kcal * (product.weight / 100))
          .reduce((prev, next) => prev + next));
      } else if (whatINeed === 'proteins') {
        return Math.round(meal.products
          .map((product) => +product.protein * (product.weight / 100))
          .reduce((prev, next) => prev + next));
      } else if (whatINeed === 'carbs') {
        return Math.round(meal.products
          .map((product) => +product.carbs * (product.weight / 100))
          .reduce((prev, next) => prev + next));
      } else if (whatINeed === 'fats') {
        return Math.round(meal.products
          .map((product) => +product.fat * (product.weight / 100))
          .reduce((prev, next) => prev + next));
      }
    } else {
      return 0;
    }
  }

  getValuesForDiet(diet: Diet, whatINeed: string): number {
    if (diet == null) {
      return null;
    }
    if (diet.meals.length > 0) {
      return diet.meals
        .map((meal) => +this.getValuesForMeal(meal, whatINeed))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  exportExcel(): void {
    const workBook = xlsx.utils.book_new();
    let workBookName = '';
    const workSheetName = 'Dieta';
    let productsTable: String[] = new Array();
    let supplementsTable: String[] = new Array();
    const xlsxDiet: any[][] = new Array();

    xlsxDiet.push(['Posiłki', 'Dni treningowe', 'Suplementy']);
    this.diet.meals.forEach(meal => {
      productsTable = meal.products.map(p => p.weight + 'g - ' + p.name);
      supplementsTable = meal.suplements.split('\n');

      for (let i = 0; i < Math.max(productsTable.length, supplementsTable.length); i++) {
        if (i === 0) {
          xlsxDiet.push([meal.mealNo.toString() + '.', productsTable[i], supplementsTable[i]]);
        } else {
          xlsxDiet.push([null, productsTable[i], supplementsTable[i]]);
        }
      }
      xlsxDiet.push([null, null, null]);
    });
    const workSheet = xlsx.utils.aoa_to_sheet(xlsxDiet);

    workSheet['!cols'] = [
      {wch: 15},
      {wch: 30},
      {wch: 30},
      {wch: 30}
    ];
    xlsx.utils.book_append_sheet(workBook, workSheet, workSheetName);

    this.clientService.getClientNewestVersion(this.clientNo).subscribe((client) => {
      workBookName = client.firstname + ' ' + client.lastname + ' - ' + this.getValuesForDiet(this.diet, 'kcal') + ' kcal.xlsx';
      xlsx.writeFile(workBook, workBookName);
    });
  }
}
