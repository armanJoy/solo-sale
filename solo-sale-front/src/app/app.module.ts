import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LandingComponent } from './landing/landing.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PurchaseComponent } from './purchase/purchase.component';
import { SaleComponent } from './sale/sale.component';
import { ProductComponent } from './product/product.component';
import { SearchFilterComponent } from './ui/search-filter/search-filter.component';
import { CommonTableComponent } from './ui/common-table/common-table.component';
import { CollapsibleTableComponent } from './ui/collapsible-table/collapsible-table.component';
import { ExpandableAccordionComponent } from './ui/expandable-accordion/expandable-accordion.component';
import { PurchaseFormComponent } from './purchase-form/purchase-form.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SaleFormComponent } from './sale-form/sale-form.component';
import { ProductFormComponent } from './product-form/product-form.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LandingComponent,
    PurchaseComponent,
    SaleComponent,
    ProductComponent,
    SearchFilterComponent,
    CommonTableComponent,
    CollapsibleTableComponent,
    ExpandableAccordionComponent,
    PurchaseFormComponent,
    SaleFormComponent,
    ProductFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, 
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
