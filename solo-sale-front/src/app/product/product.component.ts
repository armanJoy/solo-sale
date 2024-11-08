import { Component, OnInit } from '@angular/core';
import { RestService } from '../shared/rest';
import { ProductFormComponent } from '../product-form/product-form.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {

  constructor(private restService: RestService, public dialog: MatDialog) {}

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
    { name: 'Name', key: 'productName' },
    { name: 'Category', key: 'category' },
    { name: 'Brand', key: 'brand' },
    { name: 'MRP', key: 'mrp' },
    { name: 'Selling Price', key: 'sellingPrice' },
    { name: 'Stock', key: 'stock' }
  ];

  ngOnInit(): void {

    this.loadData();

    this.loadCategories(()=>{

        this.loadBrands(()=>{

            this.searchFields = [
              {
                type: 'text',
                key: 'name',
                value: '',
                placeholder: 'Search by Name'
              },
              {
                type: 'text',
                key: 'stockNumber',
                value: '',
                placeholder: 'Search by Stock Number'
              },
              {
                type: 'dropdown',
                key: 'categoryId',
                value: '',
                placeholder: 'Select Category',
                options: this.categories
              },
              {
                type: 'dropdown',
                key: 'brandId',
                value: '',
                placeholder: 'Select Brand',
                options: this.brands
              }
            ];

        });

    });
  }

  loadCategories(callBack:any): void {
    this.restService.get('/category/list').subscribe(response => {
      this.categories = response;
      callBack();
    });
  }

  loadBrands(callBack:any): void {
    this.restService.get('/brand/list').subscribe(response => {
      this.brands = response;
      callBack();
    });
  }

  loadData(): void {
    var params = {
      'pageNumber': this.page.toString(),
      'pageSize': this.pageSize.toString(),
    }
    this.searchFields.forEach((field) => {
      params[field.key] = field.value;
    });
    console.log(params);

    this.restService.search('/product/search', params ).subscribe((response) => {
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

  openProductFormDialog(productData?: any): void {
    const dialogRef = this.dialog.open(ProductFormComponent, {
      width: '800px',
      data: productData || {
        productName: '',
        brandId: 0,
        categoryId: 0,
        mrp: 0,
        sellingPrice: 0
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadData();
    });
  }
}
