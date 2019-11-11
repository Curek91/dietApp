import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {Product} from '../models/Product';
import {Diet} from '../models/Diet';
import {DietService} from '../diet.service';
import {Meal} from '../models/Meal';
import {ProductType} from '../models/ProductType';
import {IProduct} from '../models/IProduct';
import {ClientService} from "../../client/client.service";
import {ModalComponent} from "angular-custom-modal";

@Component({
  selector: 'app-new-diet',
  templateUrl: './new-diet.component.html',
  styleUrls: ['./new-diet.component.css']
})
export class NewDietComponent implements OnInit {

  @Input() clientId : number;
  @Input() dietId : number;
  @Input() oldDiets: Diet[];

  @ViewChild("createDietModal") createDietModal: ModalComponent;
  @ViewChild("updateDietModal") updateDietModal: ModalComponent;

  diet: Diet = new Diet();
  products: Product[] = new Array();
  activeMeal: number;
  filter: String = '';
  productTypeSelected: String = null;
  types: ProductType[] = new Array();
  oldDiet: Diet;
  suplements: string = '';

  constructor(private dietService: DietService, private clientService: ClientService) {
  }

  ngOnInit() {
    console.log("INIT");
    this.loadProducts();
    this.loadProductTypes();
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
    this.diet.meals[this.activeMeal - 1].products.push(prod);
  }

  moveToMeal($event: any, id: number) {
    const prod: Product = new Product($event.dragData);
    this.diet.meals[id - 1].products.push(prod);

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
  }

  setActiveMeal(id: number) {
    this.activeMeal = id;
  }

  getProteinsForMeal(meal: Meal): number {
    if (meal == null)
      return null;
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.protein * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForMeal(meal: Meal): number {
    if (meal == null)
      return null;
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.carbs  * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForMeal(meal: Meal): number {
    if (meal == null)
      return null;
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.fat  * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getEnergyForMeal(meal: Meal): number {
    if (meal == null)
      return null;
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.kcal * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getProteinsForDiet(diet: Diet): number {
    if (diet == null)
      return null;
    if (diet.meals.length > 0) {
      return diet.meals
        .map((meal) => +this.getProteinsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForDiet(diet: Diet): number {
    if (diet == null)
      return null;
    if (diet.meals.length > 0) {
      return diet.meals
        .map((meal) => +this.getCarbsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForDiet(diet: Diet): number {
    if (diet == null)
      return null;
    if (diet.meals.length > 0) {
      return diet.meals
        .map((meal) => +this.getFatsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getEnergyForDiet(diet: Diet): number {
    if (diet == null)
      return null;
    if (diet.meals.length > 0) {
      return diet.meals
        .map((meal) => +this.getEnergyForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  addDiet() {
    this.diet.clientId = this.clientId;
    this.dietService.addDiet(this.diet).subscribe((diet) => {
      console.log(diet.id);
      this.dietId = diet.id;
      console.log('dodaje diete');
    });
    this.createDietModal.close();
  }

  clearNewDiet(): void{
    this.diet = new Diet();
    this.activeMeal = 0;
  }

  getDiet(): void {
    this.dietService.getDiet(this.dietId).subscribe((diet) => {
        diet.meals.sort((leftSide, rightSide) : number => {
          if (leftSide.mealNo < rightSide.mealNo) return -1;
          if (leftSide.mealNo > rightSide.mealNo) return 1;
          return 0;
        });
        diet.meals.forEach((meal) => {
          meal.products.sort((leftSide, rightSide) : number => {
            if (leftSide.sortNo < rightSide.sortNo) return -1;
            if (leftSide.sortNo > rightSide.sortNo) return 1;
            return 0;
          });
        });
        this.diet = diet;
        this.activeMeal = 1;
      }

    )}

  modifyDiet() {
    this.diet.id = this.dietId;
    this.dietService.modifyDiet(this.diet).subscribe(() => {
      console.log('modyfikuje diete');
    });
    this.updateDietModal.close();
  }

  getOldDiet(value): void {

    if (!value){
      this.oldDiet = null;
      return;
    }

    console.log("Diet id: " + value);
    this.dietService.getDiet(value).subscribe((diet) => {
        diet.meals.sort((leftSide, rightSide) : number => {
          if (leftSide.mealNo < rightSide.mealNo) return -1;
          if (leftSide.mealNo > rightSide.mealNo) return 1;
          return 0;
        });
        diet.meals.forEach((meal) => {
          meal.products.sort((leftSide, rightSide) : number => {
            if (leftSide.sortNo < rightSide.sortNo) return -1;
            if (leftSide.sortNo > rightSide.sortNo) return 1;
            return 0;
          });
        });
        this.oldDiet = diet;
      }

    )}

    getMealOnOldDiet(mealNo: number): Meal {
      let meal: Meal;
      if (this.oldDiet) {
        meal = this.oldDiet.meals.find(meal => meal.mealNo == mealNo);
        return meal;
      } else {
        return null;
      }
    }



}
