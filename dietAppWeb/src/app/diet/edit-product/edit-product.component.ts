import {Component, OnInit} from '@angular/core';
import {ProductType} from '../models/ProductType';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {DietService} from '../diet.service';
import {Product} from '../models/Product';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  productForm: FormGroup;
  types: ProductType[] = new Array();
  afuConfig = {
    multiple: false,
    formatsAllowed: '.jpg,.png,.gif',
    maxSize: '20',
    hideResetBtn: true,
    theme: 'dragNDrop',
    replaceTexts: {
      selectFileBtn: 'Wybierz pliki',
      resetBtn: 'Reset',
      uploadBtn: 'Wyślij',
      dragNDropBox: 'Drag N Drop',
      attachPinBtn: 'Attach Files...',
      afterUploadMsg_success: 'Przesłano pomyślnie',
      afterUploadMsg_error: 'Przesłano błędnie'
    },
    uploadAPI: {
      url: 'http://localhost:8091/addImage/' + this.route.snapshot.params['id'],
      headers: {
        'Authorization': 'Bearer ' + this.authService.getToken()
      }
    }
  };

  imageToShow: any;
  isImageLoading: boolean;

  constructor(private formBuilder: FormBuilder,
              private dietService: DietService,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.productForm = this.formBuilder.group({
      type: [null, Validators.required],
      name: ['', Validators.required],
      protein: ['', Validators.required],
      carbs: ['', Validators.required],
      fat: ['', Validators.required],
      kcal: ['', Validators.required]
    });
    this.loadProduct();
    this.loadProductTypes();
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

  uploadFile(event: any) {
    const id = +this.route.snapshot.params['id'];
    this.dietService.modifyProduct(this.parseFormToEntity()).subscribe(() => {
      console.log('modyfikuje produkt');
    });
    window.location.reload();
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

  loadProductTypes(): void {
    let typeTemp: ProductType;
    this.dietService.getTypes().subscribe((types) => {
      console.log(types);
      types.forEach((type) => {

        typeTemp = {
          id: type.id,
          name: type.name,
        };
        this.types.push(typeTemp);
      });
    });
  }

  parseFormToEntity(): Product {
    let product: Product;

    product = {
      id: +this.route.snapshot.params['id'],
      type: {id: +this.productForm.value['type'], name: null},
      name: this.productForm.value['name'].toString(),
      protein: +this.productForm.value['protein'],
      carbs: +this.productForm.value['carbs'],
      fat: +this.productForm.value['fat'],
      kcal: +this.productForm.value['kcal'],
      weight: null
    };
    return product;
  }

  modifyProduct() {
    const id = +this.route.snapshot.params['id'];
    console.log(this.parseFormToEntity());
    this.dietService.modifyProduct(this.parseFormToEntity()).subscribe(() => {
      console.log('modyfikuje produkt');
    });
    this.router.navigate(['/manage-products']);
  }

  loadProduct() {
    const id = +this.route.snapshot.params['id'];
    this.dietService.getProduct(id).subscribe((product) => {
      this.productForm = this.formBuilder.group({
        type: [+product.type.id, Validators.required],
        name: [product.name, Validators.required],
        protein: [product.protein, Validators.required],
        carbs: [product.carbs, Validators.required],
        fat: [product.fat, Validators.required],
        kcal: [product.kcal, Validators.required]
      });
    });
    this.getImageFromService(id);
  }

}
