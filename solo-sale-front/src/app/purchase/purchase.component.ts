import { Component } from '@angular/core';
import { RestService } from '../shared/rest';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { PurchaseFormComponent } from '../purchase-form/purchase-form.component';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.scss']
})
export class PurchaseComponent {

  constructor(private restService: RestService, private router: Router, public dialog: MatDialog) {}

  data: any[] = [];
  page = 0;
  pageSize = 10;
  totalItems = 0;
  name = '';
  stockNumber = '';
  categoryId: string | number = '';
  brandId: string | number = '';
  categories: any[] = [];
  brands: any[] = [];
  searchFields: any[] = [];
  columns = [
    { name: 'Vendor', key: 'vendor' },
    { name: 'Purchase Date', key: 'purchaseDate' },
    { name: 'Purchase Amount', key: 'totalPrice' }
  ];
  childColumns = [
    { name: 'Product Name', key: 'productName' },
    { name: 'Quantity', key: 'quantity' },
    { name: 'Price', key: 'buyingPrice' },
    { name: 'Total Price', key: 'totalProductPrice' }
  ];
  childKey = 'purchasedProducts';

  ngOnInit(): void {

    this.loadData();
    this.searchFields = [
      {
        type: 'text',
        key: 'vendor',
        value: '',
        placeholder: 'Search by Vendor'
      },
      {
        type: 'date',
        key: 'start',
        value: '',
      },
      {
        type: 'date',
        key: 'end',
        value: ''              
      }
    ];
  }

  // loadCategories(callBack:any): void {
  //   this.restService.get('/category/list').subscribe(response => {
  //     this.categories = response;
  //     callBack();
  //   });
  // }

  // loadBrands(callBack:any): void {
  //   this.restService.get('/brand/list').subscribe(response => {
  //     this.brands = response;
  //     callBack();
  //   });
  // }

  loadData(): void {
    var params = {
      'pageNumber': this.page.toString(),
      'pageSize': this.pageSize.toString(),
    }
    this.searchFields.forEach((field) => {
      params[field.key] = field.value;
    });
    console.log(params);

    this.restService.search('/purchase/search', params ).subscribe((response) => {
      this.data = response.data;
      this.totalItems = response.totalPages;
    });
  }

  onSearchChange(): void {
    this.page = 0;
    this.loadData();
  }

  onFilterChange(filters: any): void {
    this.page = 0;
    this.searchFields.forEach(field => {
      if (filters[field.key] !== undefined) {
        field.value = filters[field.key];
      }
    });
    this.loadData();
  }

  onPageChange(page: number): void {
    this.page = page;
    this.loadData();
  }

  openPurchaseFormDialog(): void {
    console.log("On Click openPurchaseFormDialog");
    const dialogRef = this.dialog.open(PurchaseFormComponent, {
      width: '800px',
      data: {} 
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadData();
    });
  }
}
