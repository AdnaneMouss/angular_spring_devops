import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import { LoginService } from '../../services/login.service';
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";  // Adjust the import path based on your project structure

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    RouterLink
  ],
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  onLogin() {
    console.log('Attempting login...'); // Debug log
    this.loginService.login(this.email, this.password).subscribe({
      next: (response: any) => {
        console.log('Response received from API:', response); // Log response
        localStorage.setItem('user', JSON.stringify(response));
        alert(response.type);
if(response.type=="Admin"){
this.router.navigate(['/users']);
}
else if(response.type=="Employee"){
  this.router.navigate(['/products']);
}
else if(response.type=="Manager"){
  this.router.navigate(['/commandsSupplier']);
}
      },

      error: (error) => {
        console.error('Error occurred during login:', error); // Log error
        this.errorMessage = 'Invalid credentials, please try again.';
      }
    });
  }


}
