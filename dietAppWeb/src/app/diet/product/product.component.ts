import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../models/Product";
import {DietService} from "../diet.service";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  @Input() productInput: Product;

  imageToShow: any;
  isImageLoading: boolean;

  constructor(private dietService: DietService) { }

  ngOnInit() {
    console.log(this.productInput.id);
    this.getImageFromService(this.productInput.id);
  }

  createImageFromBlob(image: Blob) {
    console.log('createImageFromBlob');
    const reader = new FileReader();
    reader.addEventListener('load', () => {
      console.log(this.imageToShow);
      this.imageToShow = reader.result;
      console.log(this.imageToShow);
    }, false);

    if (image) {
      reader.readAsDataURL(image);
    }
  }

  getImageFromService(id: number) {
    console.log('getImageFromService');
    this.isImageLoading = true;
    console.log('jeeestem2');
    this.dietService.getImage(id).subscribe(data => {
      console.log('jeeestem');
      this.createImageFromBlob(data);
      this.isImageLoading = false;
    }, error => {
      this.isImageLoading = false;
      console.log(error);
    });
  }

  getTooltipText(product: Product): String {
    return 'Białko: ' + product.protein + 'g\n' +
           'Węglowodany: ' + product.carbs + 'g\n' +
           'Tłuszcze: ' + product.fat + 'g\n' +
           'Kalorie: ' + product.kcal;
  }

}
