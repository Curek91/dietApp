import {Component, Input, OnInit} from '@angular/core';
import {Product} from '../models/Product';
import {DietService} from "../diet.service";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-product-on-diet',
  templateUrl: './product-on-diet.component.html',
  styleUrls: ['./product-on-diet.component.css']
})
export class ProductOnDietComponent implements OnInit {
  @Input() productInput: Product;
  @Input() disable: boolean;

  imageToShow: any;
  isImageLoading: boolean;

  constructor(private dietService: DietService) { }

  ngOnInit() {
    console.log(this.productInput.id);
    this.getImageFromService(this.productInput.id);
  }

  createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      this.imageToShow = reader.result;
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    this.isImageLoading = true;
    this.dietService.getImage(id).subscribe(data => {
      this.createImageFromBlob(data);
      this.isImageLoading = false;
    }, error => {
      this.isImageLoading = false;
      console.log(error);
    });
  }

  getTooltipText(product: Product): String {
    return 'Białko: ' + product.protein * (product.weight / 100)  + 'g\n' +
      'Węglowodany: ' + product.carbs * (product.weight / 100)  + 'g\n' +
      'Tłuszcze: ' + product.fat * (product.weight / 100)  + 'g\n' +
      'Kalorie: ' + product.kcal * (product.weight / 100);
  }


}
