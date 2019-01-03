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
  filter: String;


  constructor(private dietService: DietService) {
  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts(): void {
    let prodTemp: Product;
    this.dietService.getProducts().subscribe((products) => {
      console.log(products);
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
          imageName: product.imageName
        };
        this.products.push(prodTemp);
      });
      console.log('koneic');
      console.log(this.products);
    });
  }

  addToMeal(product: Product): void {
    const prod: Product = new Product(product);
    this.diet.meals[this.activeMeal - 1].products.push(prod);
    console.log(this.activeMeal);
    console.log(product);
  }

  addMeal(): void {
    if (this.activeMeal == null) {
      this.diet.meals.push(new Meal(1));
      this.activeMeal = 1;
    } else {
      for (const x of this.diet.meals) {
        if (x.id > this.activeMeal) {
          x.id += 1;
        }
      }
      this.diet.meals.push(new Meal(this.activeMeal + 1));
      this.activeMeal = this.activeMeal + 1;
      this.diet.meals.sort((n1, n2) => (n1.id - n2.id));
    }
  }

  deleteFromMeal(index: number): void {
    console.log('usuwam produkt z posiłku' + index);
    this.diet.meals[this.activeMeal - 1].products.splice(index, 1);
  }

  deleteMeal(): void {
    console.log('usuwam posilek');
    const active = this.activeMeal;
    console.log('active: ' + active);
    this.diet.meals.map(function (item) {
      if (item.id > active) {
        item.id -= 1;
      }
    });
    this.diet.meals.splice(this.diet.meals.findIndex(function (element) {
      return element.id === active;
    }), 1);
    console.log('actif: ' + active);
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
        .map((product) => +product.protein)
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.carbs)
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.fat)
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getEnergyForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.kcal)
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


}
