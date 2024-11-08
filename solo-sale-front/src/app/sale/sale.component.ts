import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RestService } from '../shared/rest';
import { SaleFormComponent } from '../sale-form/sale-form.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.scss']
})
export class SaleComponent {

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
    { name: 'Date', key: 'saleDate' },
    { name: 'Customer Name', key: 'customerName' },
    { name: 'Phone', key: 'customerPhone' },
    { name: 'Address', key: 'customerAddress' },
    // {name: 'Product Price', key:'totalProductPrice'},
    // {name: 'Discount', key:'discountPercentage'},
    {name: 'Delivery', key:'deliveryCharge'},
    { name: 'Bill Amount', key: 'totalPrice' }
  ];
  childColumns = [
    { name: 'Product Name', key: 'productName' },
    { name: 'Quantity', key: 'quantity' },
    { name: 'Price', key: 'sellingPrice' },
    { name: 'Total Price', key: 'totalPrice' }
  ];
  childKey = 'soldProducts';

  ngOnInit(): void {

    this.loadData();

    this.searchFields = [
      {
        type: 'text',
        key: 'customerName',
        value: '',
        placeholder: 'Search by Customer Name'
      },
      {
        type: 'text',
        key: 'customerPhone',
        value: '',
        placeholder: 'Search by Customer Phone'
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

  loadData(): void {
    var params = {
      'pageNumber': this.page.toString(),
      'pageSize': this.pageSize.toString(),
    }
    this.searchFields.forEach((field) => {
      params[field.key] = field.value;
    });

    this.restService.search('/sale/search', params ).subscribe((response) => {
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

  openSalesFormDialog(): void {
    const dialogRef = this.dialog.open(SaleFormComponent, {
      width: '800px',
      data: {} 
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadData();
    });
  }
}
