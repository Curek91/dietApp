import {Component, OnInit} from '@angular/core';
import {Product} from '../models/Product';
import {Diet} from '../models/Diet';
import {DietService} from '../diet.service';
import {Meal} from '../models/Meal';
import {ProductType} from '../models/ProductType';
import {IProduct} from '../models/IProduct';

@Component({
  selector: 'app-new-diet',
  templateUrl: './new-diet.component.html',
  styleUrls: ['./new-diet.component.css']
})
export class NewDietComponent implements OnInit {

  diet: Diet = new Diet();
  products: Product[] = new Array();
  activeMeal: number;
  filter: String = '';
  productTypeSelected: String = null;
  types: ProductType[] = new Array();
  suplements: string = '';

  constructor(private dietService: DietService) {
  }

  ngOnInit() {
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
          weight: 0
        };
        this.products.push(prodTemp);
      });
    });
  }

  addToMeal(product: Product): void {
    console.log('productTypeSelected: ' + this.productTypeSelected);
    const prod: Product = new Product(product);
    this.diet.meals[this.activeMeal - 1].products.push(prod);
  }

  moveToMeal($event: any, id: number) {
    console.log(id);
    console.log(this.diet);
    const prod: Product = new Product($event.dragData);
    console.log(prod.name);
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
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.protein * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.carbs  * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.fat  * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getEnergyForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.kcal * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getProteinsForDiet(): number {
    if (this.diet.meals.length > 0) {
      return this.diet.meals
        .map((meal) => +this.getProteinsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForDiet(): number {
    if (this.diet.meals.length > 0) {
      return this.diet.meals
        .map((meal) => +this.getCarbsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForDiet(): number {
    if (this.diet.meals.length > 0) {
      return this.diet.meals
        .map((meal) => +this.getFatsForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getEnergyForDiet(): number {
    if (this.diet.meals.length > 0) {
      return this.diet.meals
        .map((meal) => +this.getEnergyForMeal(meal))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  addDiet() {
    console.log(this.diet);
    this.dietService.addDiet(this.diet).subscribe((diet) => {
      console.log('dodaje diet');
    });
  }

  addSuplements(): void {
    // this.diet.meals[this.activeMeal - 1].suplements = this.suplements;
    console.log('Suplementy dla posilku ' + this.activeMeal + ': ' + this.diet.meals[this.activeMeal - 1].suplements);
  }
}
