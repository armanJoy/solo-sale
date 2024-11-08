import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RestService } from '../shared/rest';
import { ToastService } from '../shared/toast.service';

@Component({
  selector: 'app-purchase-form',
  templateUrl: './purchase-form.component.html',
  styleUrls: ['./purchase-form.component.scss']
})
export class PurchaseFormComponent implements OnInit {
  purchaseForm: FormGroup;

  // Sample product options for dropdown
  products = [];

  constructor(
    private restService: RestService,
    private toastService: ToastService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<PurchaseFormComponent>, // Inject dialog reference
    @Inject(MAT_DIALOG_DATA) public data: any // Inject data if needed
  ) {
    this.purchaseForm = this.fb.group({
      vendor: ['', Validators.required],
      purchaseDate: [new Date().toISOString().split('T')[0], Validators.required],
      purchasedProducts: this.fb.array([this.createProductGroup()])
    });
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.restService.get('/product/list').subscribe(response => {
      this.products = response;
    });
  }

  createProductGroup(): FormGroup {
    return this.fb.group({
      productId: [null, Validators.required],
      quantity: [0, [Validators.required, Validators.min(1)]],
      price: [0, [Validators.required, Validators.min(0.01)]]
    });
  }

  get purchasedProducts(): FormArray {
    return this.purchaseForm.get('purchasedProducts') as FormArray;
  }

  addProduct() {
    this.purchasedProducts.push(this.createProductGroup());
  }

  removeProduct(index: number) {
    this.purchasedProducts.removeAt(index);
  }

  onSubmit() {
    if (this.purchaseForm.valid) {
      this.restService.save("/purchase", this.purchaseForm.value).subscribe(res=>{
        if(res && !res.error){
          this.toastService.showToast("success", "Purchase information saved");
        }else{
          this.toastService.showToast("error", "Couldn't saved");
        }
      })
      this.dialogRef.close(this.purchaseForm.value);
    }
  }

  // Close dialog without submitting
  onCancel(): void {
    this.dialogRef.close();
  }
}
