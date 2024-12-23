import { Component, OnInit } from '@angular/core';
import { Product } from "../../../models/product.model";
import { ProductService } from "../../../services/product.service";

@Component({
  selector: 'app-products-dashboard',
  templateUrl: './products-dashboard.component.html',
  styleUrls: ['./products-dashboard.component.css']
})
export class ProductsDashboardComponent implements OnInit {
  products: Product[] = [];
  categories: any[] = []; // To store categories for filtering
  filteredProducts: Product[] = []; // To store filtered products
  errorMessage: string = '';
  newProduct: Product = {
    id: 0,
    name: '',
    price: 0,
    image: '',
    description: '',
    stock: 0,
    categoryId: 0
  };
  showForm = false; // Manage the visibility of the form
  filterCategoryId: number | null = null; // For filtering by category
  sortOrder: string = 'Low-High'; // Default sort order

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts();
    this.fetchCategories();
  }

  fetchProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        console.log('fetched prod:',data)
        this.products = data;
        this.filteredProducts = data; // Initialize filtered products
        this.sortProducts(); // Apply initial sorting
      },
      error: (err) => {
        console.error('Error fetching products:', err);
        this.errorMessage = 'Failed to load products';
      }
    });
  }

  fetchCategories(): void {
    this.productService.getCategories().subscribe({
      next: (data) => {
        this.categories = data;
      },
      error: (err) => {
        console.error('Error fetching categories:', err);
        this.errorMessage = 'Failed to load categories';
      }
    });
  }

  addProduct(): void {
    this.productService.addProduct(this.newProduct).subscribe({
      next: () => {
        alert('Product added successfully.');
        this.fetchProducts(); // Refresh the product list
        this.resetNewProduct(); // Reset the form
        this.showForm = false; // Hide form after submission
      },
      error: (err) => {
        console.error('Error adding product:', err);
        this.errorMessage = 'Failed to add product';
      }
    });
  }

  resetNewProduct(): void {
    this.newProduct = {
      id: 0,
      name: '',
      price: 0,
      image: '',
      description: '',
      stock: 0,
      categoryId: 0
    };
  }

  toggleForm(): void {
    this.showForm = !this.showForm; // Toggle form visibility
  }

  deleteProduct(id: number): void {
    if (confirm(`Are you sure you want to delete the product with ID ${id}?`)) {
      this.productService.deleteProduct(id).subscribe({
        next: () => {
          this.fetchProducts(); // Refresh the product list
          alert('Product deleted successfully.');
        },
        error: (err) => {
          console.error('Error deleting product:', err);
          this.errorMessage = 'Failed to delete product';
        }
      });
    }
  }

  filterByCategory(): void {
    if (this.filterCategoryId) {
      this.filteredProducts = this.products.filter(
        (product) => product.categoryId === this.filterCategoryId
      );
    } else {
      this.filteredProducts = this.products; // Reset to all products
    }
    this.sortProducts(); // Apply sorting after filtering
  }

  sortProducts(): void {
    if (this.sortOrder === 'Low-High') {
      this.filteredProducts.sort((a, b) => a.price - b.price);
    } else if (this.sortOrder === 'High-Low') {
      this.filteredProducts.sort((a, b) => b.price - a.price);
    }
  }

  getCategoryName(categoryId: number): string {
    const category = this.categories.find((cat) => cat.id === categoryId);
    return category ? category.name : 'Unknown';
  }
}
