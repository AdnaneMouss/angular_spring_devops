import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { CommandsDashboardComponent } from './components/admin/commands-dashboard/commands-dashboard.component';
import { UsersDashboardComponent } from './components/admin/users-dashboard/users-dashboard.component';
import { CategoriesDashboardComponent } from './components/admin/categories-dashboard/categories-dashboard.component';
import { ProductsDashboardComponent } from './components/admin/products-dashboard/products-dashboard.component';
import { CommandsPageComponent } from './components/supplier/commands-page/commands-page.component';
import {HttpClient, HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    CommandsDashboardComponent,
    UsersDashboardComponent,
    CategoriesDashboardComponent,
    ProductsDashboardComponent,
    CommandsPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    // Enable fetch for HTTP requests
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
