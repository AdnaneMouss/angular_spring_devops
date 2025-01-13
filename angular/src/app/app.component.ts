import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { LoggingService } from './services/logging.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoginPage = false;

  constructor(private router: Router, private loggingService: LoggingService) {
    // Listen to route changes and determine if the current route is 'login'
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.isLoginPage = this.router.url === '/login'; // Adjust if your login route is different
      }
    });
  }
  ngOnInit(): void {
    // Envoyer des logs
    this.loggingService.log('Application Angular démarrée', 'info');
    this.loggingService.log('Ceci est un avertissement', 'warn');
  }
}
