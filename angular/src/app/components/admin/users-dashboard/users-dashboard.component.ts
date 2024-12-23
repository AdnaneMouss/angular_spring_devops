import { Component } from '@angular/core';
import { User } from "../../../models/user.model";
import { UserService } from "../../../services/user.service";

@Component({
  selector: 'app-users-dashboard',
  templateUrl: './users-dashboard.component.html',
  styleUrls: ['./users-dashboard.component.css']
})
export class UsersDashboardComponent {
  users: User[] = [];
  filteredUsers: User[] = []; // For filters and sorting
  userTypes: string[] = ['Admin', 'Employee', 'Supplier']; // Available user types
  selectedFilter: string = ''; // Current filter selection
  isSortedAZ: boolean = true; // For sorting toggle
  errorMessage: string = '';
  newUser: User = {
    id: 0,
    name: '',
    email: '',
    gsm: '',
    type: '',
    password: '',
    image: '',
  };
  passwordVisible = false;
  showForm = false; // Manage the visibility of the form

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.filteredUsers = data; // Initialize filteredUsers with all users
      },
      error: (err) => {
        console.error('Error fetching users:', err);
        this.errorMessage = 'Failed to load users';
      }
    });
  }

  addUser(): void {
    this.userService.addUser(this.newUser).subscribe({
      next: () => {
        alert('User added successfully.');
        this.fetchUsers(); // Refresh the user list
        this.resetNewUser(); // Reset the form
        this.showForm = false; // Hide form after submission
      },
      error: (err) => {
        console.error('Error adding user:', err);
        this.errorMessage = 'Failed to add user';
      }
    });
  }

  resetNewUser(): void {
    this.newUser = {
      id: 0,
      name: '',
      email: '',
      gsm: '',
      type: '',
      password: '',
      image: '',
    };
  }

  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
    const passwordField = document.getElementById('password') as HTMLInputElement;
    if (passwordField) {
      passwordField.type = this.passwordVisible ? 'text' : 'password';
    }
  }

  toggleForm(): void {
    this.showForm = !this.showForm; // Toggle form visibility
  }

  deleteUser(id: number): void {
    if (confirm(`Are you sure you want to delete the user with ID ${id}?`)) {
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.fetchUsers(); // Refresh the user list
          alert('User deleted successfully.');
        },
        error: (err) => {
          console.error('Error deleting user:', err);
          this.errorMessage = 'Failed to delete user';
        }
      });
    }
  }

  // Filter users by type
  filterByType(): void {
    if (this.selectedFilter) {
      this.filteredUsers = this.users.filter(user => user.type === this.selectedFilter);
    } else {
      this.filteredUsers = this.users; // Reset to all users if no filter is applied
    }
  }

  // Sort users by name A-Z or Z-A
  sortByName(): void {
    if (this.isSortedAZ) {
      this.filteredUsers.sort((a, b) => a.name.localeCompare(b.name));
    } else {
      this.filteredUsers.sort((a, b) => b.name.localeCompare(a.name));
    }
    this.isSortedAZ = !this.isSortedAZ; // Toggle sorting order
  }
}
