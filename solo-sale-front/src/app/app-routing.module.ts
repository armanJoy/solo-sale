import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingComponent } from "src/app/landing/landing.component";
import { LoginComponent } from "src/app/login/login.component";
import { ProductComponent } from './product/product.component';
import { PurchaseComponent } from './purchase/purchase.component';
import { SaleComponent } from './sale/sale.component';
import { AuthGuard } from './shared/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'product', component: ProductComponent, canActivate: [AuthGuard] },
  { path: 'purchase', component: PurchaseComponent, canActivate: [AuthGuard] },
  { path: 'sale', component: SaleComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/product', pathMatch: 'full' } // Default to product page for authenticated users
];


// const routes: Routes = [
//   { path: 'login', component: LoginComponent },
//   // { path: '', component: LandingComponent },
//   { path: 'product', component: ProductComponent },
//   { path: 'purchase', component: PurchaseComponent },
//   { path: 'sale', component: SaleComponent },
//   // { path: '', redirectTo: '/home', pathMatch: 'full' }
// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
