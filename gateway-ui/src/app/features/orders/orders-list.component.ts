import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { OrderApiService, Order } from './order-api.service';
import { OrderStatusPipe } from '../../shared/order-status.pipe';
import { HasRoleDirective } from '../../shared/has-role.directive';

@Component({
  standalone: true,
  imports: [RouterLink, OrderStatusPipe, HasRoleDirective],
  template: `
  <h2>Orders</h2>

  <div class="card">
    <strong>Create quick order</strong>
    <div style="display:flex;gap:8px;align-items:center;margin-top:8px;flex-wrap:wrap">
      <button class="btn" (click)="createSample()">Create sample order</button>
      <span class="pill">SKU-1 x1, SKU-2 x2</span>
      <button class="btn" *appHasRole="'ADMIN'" (click)="reload()">Admin: Reload</button>
    </div>
  </div>

  <div class="card" *ngIf="loading">Loading…</div>

  <div class="card" *ngFor="let o of orders">
    <div style="display:flex;justify-content:space-between;gap:10px;flex-wrap:wrap">
      <div>
        <div><strong>Order #{{o.id}}</strong></div>
        <div>Status: <span class="pill">{{ o.status | orderStatus }}</span></div>
        <div>Created: {{o.createdAt}}</div>
      </div>
      <div><a class="btn" [routerLink]="['/orders', o.id]">View</a></div>
    </div>
  </div>
  `
})
export class OrdersListComponent {
  orders: Order[] = [];
  loading = false;

  constructor(private api: OrderApiService) { this.reload(); }

  reload() {
    this.loading = true;
    this.api.listOrders().subscribe({
      next: x => { this.orders = x; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  createSample() {
    this.api.createOrder({ lines: [{ sku: 'SKU-1', qty: 1 }, { sku: 'SKU-2', qty: 2 }] })
      .subscribe({ next: () => this.reload() });
  }
}
