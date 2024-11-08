import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestService } from '../shared/rest';
import { ToastService } from '../shared/toast.service';

@Component({
  selector: 'app-sale-form',
  templateUrl: './sale-form.component.html',
  styleUrls: ['./sale-form.component.scss']
})
export class SaleFormComponent implements OnInit{
  
  products = [];
  saleForm: FormGroup;
  finalAmount = 0;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<SaleFormComponent>,
    private restService: RestService,
    private toastService: ToastService,
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.saleForm = this.fb.group({
      soldProducts: this.fb.array([this.createProductFormGroup()]),
      saleDate: [new Date().toISOString().split('T')[0], Validators.required],
      customerName: ['', Validators.required],
      customerPhone: [
        '',
        [Validators.required, Validators.pattern(/^01\d{9}$/)],
      ],
      customerAddress: ['', Validators.required],
      discountPercentage: [0, [Validators.min(0), Validators.max(100)]],
      deliveryCharge: [0, Validators.min(0)],
    });

    this.saleForm.valueChanges.subscribe(() => this.calculateFinalAmount());
  }

  loadProducts(): void {
    this.restService.get('/product/list').subscribe(response => {
      this.products = response;
    });
  }

  createProductFormGroup(): FormGroup {
    return this.fb.group({
      productId: [null, Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
    });
  }

  get soldProducts(): FormArray {
    return this.saleForm.get('soldProducts') as FormArray;
  }

  addProduct(): void {
    this.soldProducts.push(this.createProductFormGroup());
  }

  removeProduct(index: number): void {
    this.soldProducts.removeAt(index);
    this.calculateFinalAmount();
  }

  calculateFinalAmount(): void {
    
    const discount = this.saleForm.value.discountPercentage || 0;
    const deliveryCharge = this.saleForm.value.deliveryCharge || 0;

    let totalProductPrice = 0;  
    console.log(this.soldProducts.value)

    this.soldProducts.value.forEach((control) => {
      console.log(control);
      const product = this.products.find(
        (p) => p.id == control.productId
      );
      console.log(product);
      if (product) {
        totalProductPrice += product.sellingPrice * control.quantity;
      }
    });
    
    console.log(this.products, totalProductPrice);
    const discountAmount = (totalProductPrice * discount) / 100;
    this.finalAmount = totalProductPrice - discountAmount + deliveryCharge;
  }

  onSubmit() {
    if (this.saleForm.valid) {
      this.restService.save("/sale", this.saleForm.value).subscribe(res=>{
        if(res && !res.error){
          this.toastService.showToast("success", "Sales information saved");
        }else{
          this.toastService.showToast("error", "Couldn't saved");
        }
      })
      this.dialogRef.close(this.saleForm.value);
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
