import { Component, OnInit } from '@angular/core';
import {Product} from "../models/Product";
import {Diet} from "../models/Diet";
import {DietService} from "../diet.service";
import {Meal} from "../models/Meal";

@Component({
  selector: 'app-new-diet',
  templateUrl: './new-diet.component.html',
  styleUrls: ['./new-diet.component.css']
})
export class NewDietComponent implements OnInit {

  diet: Diet = new Diet();
  products: Product[];
  activeMeal: Number;
  filter: String;
  productType = ['Wszystko', 'Owoce', 'Mięso'];
  productTypeSelected: String = 'Wszystko';
  modalSuplements: String = '';

  constructor(private dietService: DietService) { }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts(): void {
    this.dietService.getProducts().subscribe((products) => {
      this.products = products;
    });
  }

  addToMeal(product: Product): void {
    const prod: Product = new Product(product);
    this.diet.meals[this.activeMeal - 1].products.push(prod);
    console.log(this.activeMeal);
    console.log(product);
  }

  addMeal(): void {
    console.log('dodaje posilek');
    this.diet.meals.push(new Meal(this.diet.meals.length + 1));
  }

  deleteFromMeal(index: number): void {
    console.log('usuwam produkt z posiłku' + index);
    this.diet.meals[this.activeMeal - 1].products.splice(index, 1);
  }

  deleteMeal(): void {
    console.log('usuwam posilek');
    this.diet.meals.splice(this.activeMeal - 1, 1);
    this.activeMeal = this.activeMeal - 1;
  }

  setActiveMeal(id: Number) {
    this.activeMeal = id;
  }

  getProteinsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return  meal.products
        .map((product) => +product.protein * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getCarbsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.carb * (product.weight / 100))
        .reduce((prev, next) => prev + next);
    } else {
      return 0;
    }
  }

  getFatsForMeal(meal: Meal): number {
    if (meal.products.length > 0) {
      return meal.products
        .map((product) => +product.fat * (product.weight / 100))
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


}
