import { Component, OnInit } from '@angular/core';
import { Order } from "../../../models/order.model";
import { OrderService } from "../../../services/order.service";
import { ProductService } from "../../../services/product.service";
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'app-commands-dashboard',
  templateUrl: './commands-dashboard.component.html',
  styleUrls: ['./commands-dashboard.component.css']
})
export class CommandsDashboardComponent implements OnInit {
  orders: Order[] = [];
  users: any[] = []; // To store users for orderers and suppliers
  products: any[] = []; // To store products
  errorMessage: string = '';
  userID: number | null = null; // To store the connected user's ID
  newOrder: Order = {
    id: 0,
    ordererId: 0,
    supplierId: 0,
    productId: 0,
    productName: '',
    quantity: 0,
    deliveryDate: null, // Change this to null or an initial date
    approved: false
  };

  showForm = false;

  constructor(
    private orderService: OrderService,
    private productService: ProductService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.setConnectedUserID(); // Set the connected user's ID
    this.fetchOrders();
    this.fetchUsers();
    this.fetchProducts();
  }

  setConnectedUserID(): void {
    const user = JSON.parse(localStorage.getItem('user') || '{}');
    this.userID = user?.id || null;
  }

  fetchOrders(): void {
    this.orderService.getOrders().subscribe({
      next: (data) => {
        this.orders = data;
      },
      error: (err) => {
        console.error('Error fetching orders:', err);
        this.errorMessage = 'Failed to load orders';
      }
    });
  }

  fetchUsers(): void {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Error fetching users:', err);
        this.errorMessage = 'Failed to load users';
      }
    });
  }

  fetchProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data;
      },
      error: (err) => {
        console.error('Error fetching products:', err);
        this.errorMessage = 'Failed to load products';
      }
    });
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
    if (this.showForm && this.userID) {
      // Automatically set the orderer ID to the connected user
      this.newOrder.ordererId = this.userID;
    }
  }

  addOrder(): void {
    this.orderService.addOrder(this.newOrder).subscribe({
      next: () => {
        alert('Order added successfully.');
        this.fetchOrders(); // Refresh the orders list
        this.resetNewOrder(); // Reset the form
        this.showForm = false; // Hide the form
      },
      error: (err) => {
        console.error('Error adding order:', err);
        this.errorMessage = 'Failed to add order';
      }
    });
  }

  resetNewOrder(): void {
    this.newOrder = {
      id: 0,
      ordererId: this.userID || 0, // Ensure orderer ID stays set for connected user
      supplierId: 0,
      productId: 0,
      productName: '',
      quantity: 0,
      deliveryDate: null,
      approved: false
    };
  }

  deleteOrder(id: number): void {
    if (confirm(`Are you sure you want to delete the order with ID ${id}?`)) {
      this.orderService.deleteOrder(id).subscribe({
        next: () => {
          this.fetchOrders(); // Refresh the orders list
          alert('Order deleted successfully.');
        },
        error: (err) => {
          console.error('Error deleting order:', err);
          this.errorMessage = 'Failed to delete order';
        }
      });
    }
  }

  getOrdererName(ordererId: number): string {
    const orderer = this.users.find((user) => user.id === ordererId);
    return orderer ? orderer.name : 'Unknown';
  }

  getSupplierName(supplierId: number): string {
    const supplier = this.users.find((user) => user.id === supplierId);
    return supplier ? supplier.name : 'Unknown';
  }

  getProductName(productId: number): string {
    const product = this.products.find((product) => product.id === productId);
    return product ? product.name : 'Unknown';
  }
}
