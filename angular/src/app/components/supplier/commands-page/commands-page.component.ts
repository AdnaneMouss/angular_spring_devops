import { Component, OnInit } from '@angular/core';
import { Order } from "../../../models/order.model";
import { OrderService } from "../../../services/order.service";

@Component({
  selector: 'app-commands-page',
  templateUrl: './commands-page.component.html',
  styleUrls: ['./commands-page.component.css']
})
export class CommandsPageComponent implements OnInit {
  orders: Order[] = [];
  filteredOrders: Order[] = []; // To store orders for the connected supplier
  errorMessage: string = '';
  userID: string | null = null; // Logged-in user's ID

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.getLoggedInUser(); // Get logged-in user details
    this.fetchOrders(); // Fetch orders
  }

  // Get logged-in user details
  getLoggedInUser(): void {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    this.userID = user?.id || null; // Assuming "id" contains the user ID
  }

  // Fetch all orders and filter them for the logged-in supplier
  fetchOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (data) => {
        this.orders = data;
        this.filterOrders(); // Filter orders for the logged-in supplier
      },
      error: (err) => {
        console.error('Error fetching orders:', err);
        this.errorMessage = 'Failed to load orders';
      }
    });
  }

  // Filter orders by supplier ID
  filterOrders(): void {
    this.filteredOrders = this.orders.filter(
      (order) => order.supplierId === parseInt(this.userID || '', 10)
    );
  }

  // Approve an order
  approveOrder(orderId: number): void {
    this.orderService.approveOrder(orderId).subscribe({
      next: () => {
        alert(`Order ID ${orderId} approved successfully.`);
        this.fetchOrders(); // Refresh orders list
      },
      error: (err) => {
        console.error('Error approving order:', err);
        this.errorMessage = 'Failed to approve order';
      }
    });
  }

  // Delete an order
  deleteOrder(orderId: number): void {
    if (confirm(`Are you sure you want to delete the order with ID ${orderId}?`)) {
      this.orderService.deleteOrder(orderId).subscribe({
        next: () => {
          alert(`Order ID ${orderId} deleted successfully.`);
          this.fetchOrders(); // Refresh orders list
        },
        error: (err) => {
          console.error('Error deleting order:', err);
          this.errorMessage = 'Failed to delete order';
        }
      });
    }
  }
}
