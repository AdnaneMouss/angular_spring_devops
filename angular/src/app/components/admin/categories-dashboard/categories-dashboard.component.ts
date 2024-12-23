import { Component } from '@angular/core';
import { CategoryService } from "../../../services/category.service";

@Component({
  selector: 'app-categories-dashboard',
  templateUrl: './categories-dashboard.component.html',
  styleUrls: ['./categories-dashboard.component.css']
})
export class CategoriesDashboardComponent {
  categories: any[] = [];
  filteredCategories: any[] = [];
  errorMessage: string = '';
  newCategory: any = {
    id: 0,
    name: '',
    image: ''
  };
  showForm = false;
  selectedCategory: string = ''; // Dropdown filter value
  isSortedAZ: boolean = true; // Sort toggle state

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getCategories().subscribe({
      next: (data) => {
        this.categories = data;
        this.filteredCategories = [...this.categories];
      },
      error: (err) => {
        console.error('Error fetching categories:', err);
        this.errorMessage = 'Failed to load categories';
      }
    });
  }

  addCategory(): void {
    if (this.newCategory.name && this.newCategory.image) {
      this.categoryService.addCategory(this.newCategory).subscribe({
        next: () => {
          alert('Category added successfully.');
          this.fetchCategories();
          this.resetNewCategory();
          this.showForm = false;
        },
        error: (err) => {
          console.error('Error adding category:', err);
          this.errorMessage = 'Failed to add category';
        }
      });
    }
  }

  resetNewCategory(): void {
    this.newCategory = {
      id: 0,
      name: '',
      image: ''
    };
  }

  toggleForm(): void {
    this.showForm = !this.showForm;
  }

  deleteCategory(id: number): void {
    if (confirm(`Are you sure you want to delete the category with ID ${id}?`)) {
      this.categoryService.deleteCategory(id).subscribe({
        next: () => {
          this.fetchCategories();
          alert('Category deleted successfully.');
        },
        error: (err) => {
          console.error('Error deleting category:', err);
          this.errorMessage = 'Failed to delete category';
        }
      });
    }
  }

  // Filter categories by dropdown selection
  filterCategoriesByDropdown(): void {
    if (this.selectedCategory) {
      this.filteredCategories = this.categories.filter(
        category => category.name === this.selectedCategory
      );
    } else {
      this.filteredCategories = [...this.categories];
    }
  }

  // Sort categories A-Z or Z-A
  sortCategories(): void {
    this.filteredCategories.sort((a, b) =>
      this.isSortedAZ
        ? a.name.localeCompare(b.name)
        : b.name.localeCompare(a.name)
    );
    this.isSortedAZ = !this.isSortedAZ;
  }
}
