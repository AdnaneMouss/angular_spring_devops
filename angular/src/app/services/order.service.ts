import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/api/orders'; // Replace with your backend's actual URL
  private usersUrl = 'http://localhost:8080/api/user'; // For fetching users (if needed)
  private productsUrl = 'http://localhost:8080/api/product'; // For fetching products (if needed)

  constructor(private http: HttpClient) {}

  /**
   * Fetch all orders.
   * @returns Observable of the order list.
   */
  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUrl}/list`);
  }

  /**
   * Add a new order.
   * @param order The order to be added.
   * @returns Observable for the post request.
   */
  addOrder(order: Order): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/add`, order);
  }

  /**
   * Delete an order by ID.
   * @param id The ID of the order to be deleted.
   * @returns Observable for the delete request.
   */
  deleteOrder(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/${id}`);
  }

  /**
   * Fetch all users.
   * This method is optional if you need users for dropdown or validations.
   * @returns Observable of user list.
   */
  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.usersUrl}/list`);
  }

  /**
   * Fetch all products.
   * This method is optional if you need products for dropdown or validations.
   * @returns Observable of product list.
   */
  getProducts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.productsUrl}/list`);
  }
}
