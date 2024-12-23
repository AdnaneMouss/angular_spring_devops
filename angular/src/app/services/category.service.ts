import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private apiUrl = 'http://localhost:8080/api/category'; // Update the URL if necessary

  constructor(private http: HttpClient) {}

  getCategories(): Observable<User[]> {
    const url = `${this.apiUrl}/list`;
    return this.http.get<User[]>(url);
  }

  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  addCategory(user: any): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add`, user);
  }

}
