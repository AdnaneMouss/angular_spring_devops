import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { CommandsDashboardComponent } from './components/admin-employee/commands-dashboard/commands-dashboard.component';
import { UsersDashboardComponent } from './components/admin/users-dashboard/users-dashboard.component';
import { CategoriesDashboardComponent } from './components/admin/categories-dashboard/categories-dashboard.component';
import { ProductsDashboardComponent } from './components/admin-employee/products-dashboard/products-dashboard.component';
import { CommandsPageComponent } from './components/supplier/commands-page/commands-page.component';
import {HttpClient, HttpClientModule, provideHttpClient, withFetch} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    CommandsDashboardComponent,
    UsersDashboardComponent,
    CategoriesDashboardComponent,
    ProductsDashboardComponent,
    CommandsPageComponent,
    HeaderComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        LoginPageComponent,
        FormsModule
    ],
  providers: [
    // Enable fetch for HTTP requests
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
