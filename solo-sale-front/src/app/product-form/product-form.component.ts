import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RestService } from '../shared/rest';
import { ToastService } from '../shared/toast.service';

interface ProductData {
  id:number;
  productName: string;
  brandId: number;
  categoryId: number;
  mrp: number;
  sellingPrice: number;
}

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  brands = [];
  categories = [];

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ProductFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ProductData,
    private restService: RestService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.loadCategories();

    this.loadBrands();

    this.productForm = this.fb.group({
      productName: [this.data.productName || '', Validators.required],
      brandId: [this.data.brandId || '', Validators.required],
      categoryId: [this.data.categoryId || '', Validators.required],
      mrp: [this.data.mrp || 0, [Validators.required, Validators.min(1)]],
      sellingPrice: [
        this.data.sellingPrice || 0,
        [Validators.required, Validators.min(1)],
      ],
    });
  }

  loadCategories(): void {
    this.restService.get('/category/list').subscribe(response => {
      this.categories = response;
    });
  }

  loadBrands(): void {
    this.restService.get('/brand/list').subscribe(response => {
      this.brands = response;
    });
  }

  onSubmit() {
    if (this.productForm.valid) {
      if(this.data.id){
        this.restService.update("/product", this.data.id, this.productForm.value).subscribe(res=>{
          if(res && !res.error){
            this.toastService.showToast("success", "Sales information saved");
          }else{
            this.toastService.showToast("error", "Couldn't saved");
          }
        })
      }else{
        this.restService.save("/product", this.productForm.value).subscribe(res=>{
          if(res && !res.error){
            this.toastService.showToast("success", "Sales information saved");
          }else{
            this.toastService.showToast("error", "Couldn't saved");
          }
        })
      }
      
      this.dialogRef.close(this.productForm.value);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
