import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {LoginPageComponent} from "./components/login-page/login-page.component";
import {UsersDashboardComponent} from "./components/admin/users-dashboard/users-dashboard.component";
import {CategoriesDashboardComponent} from "./components/admin/categories-dashboard/categories-dashboard.component";
import {AuthGuard} from "./auth/auth.guard";
import {ProductsDashboardComponent} from "./components/admin-employee/products-dashboard/products-dashboard.component";
import {CommandsDashboardComponent} from "./components/admin-employee/commands-dashboard/commands-dashboard.component";
import {CommandsPageComponent} from "./components/supplier/commands-page/commands-page.component";

const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: 'users', component: UsersDashboardComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'products', component: ProductsDashboardComponent, canActivate: [AuthGuard], data: { roles: ['Admin', 'Employee'] } },
  { path: 'categories', component: CategoriesDashboardComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'commands', component: CommandsDashboardComponent, canActivate: [AuthGuard], data: { roles: ['Admin','Employee'] } },
  { path: 'commandsSupplier', component: CommandsPageComponent, canActivate: [AuthGuard], data: { roles: ['Manager'] } },
  { path: '**', redirectTo: 'login', pathMatch: 'full' }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
