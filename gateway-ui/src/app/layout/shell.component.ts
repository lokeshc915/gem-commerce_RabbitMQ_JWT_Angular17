import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../core/auth.service';

@Component({
  standalone: true,
  selector: 'app-shell',
  imports: [RouterLink, RouterOutlet],
  template: `
  <div class="top">
    <strong>GEM Commerce</strong>
    <a routerLink="/orders">Orders</a>
    <a routerLink="/inventory">Inventory</a>
    <a routerLink="/products">Products</a>
    <a routerLink="/notifications">Notifications</a>
    <span style="flex:1"></span>
    <span class="pill">User: {{auth.username()}}</span>
    <button class="btn" (click)="auth.logout()">Logout</button>
  </div>

  <div class="layout">
    <div class="side">
      <div class="card">
        <div><strong>Navigation</strong></div>
        <div style="margin-top:8px;display:grid;gap:6px">
          <a routerLink="/orders">Orders</a>
          <a routerLink="/inventory">Inventory</a>
          <a routerLink="/products">Products</a>
          <a routerLink="/notifications">Notifications</a>
        </div>
      </div>
      <div class="card">
        <div><strong>Angular features</strong></div>
        <ul>
          <li>Routing + Guard</li>
          <li>Resolver (Order Detail)</li>
          <li>HTTP Interceptor (JWT)</li>
          <li>Custom Directive (*appHasRole)</li>
          <li>Custom Pipe (orderStatus)</li>
        </ul>
      </div>
    </div>
    <div class="main">
      <router-outlet />
    </div>
  </div>
  `
})
export class ShellComponent {
  constructor(public auth: AuthService) {}
}
