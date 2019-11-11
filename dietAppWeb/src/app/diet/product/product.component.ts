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
    return 'Białko: ' + product.protein + 'g\n' +
           'Węglowodany: ' + product.carbs + 'g\n' +
           'Tłuszcze: ' + product.fat + 'g\n' +
           'Kalorie: ' + product.kcal;
  }

}
