import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { OrderApiService, Order } from './order-api.service';
import { OrderStatusPipe } from '../../shared/order-status.pipe';
import { HasRoleDirective } from '../../shared/has-role.directive';

@Component({
  standalone: true,
  imports: [RouterLink, OrderStatusPipe, HasRoleDirective],
  template: `
  <a routerLink="/orders">← Back</a>
  <div class="card" *ngIf="order">
    <h2>Order #{{order.id}}</h2>
    <div>Status: <span class="pill">{{order.status | orderStatus}}</span></div>

    <h3>Lines</h3>
    <ul><li *ngFor="let l of order.lines">{{l.sku}} x {{l.qty}}</li></ul>

    <button class="btn" *appHasRole="'ADMIN'" (click)="cancel()">Admin: Cancel</button>
  </div>
  `
})
export class OrderDetailComponent {
  order: Order | null;
  constructor(private route: ActivatedRoute, private api: OrderApiService) {
    this.order = this.route.snapshot.data['order'];
  }
  cancel() {
    if (!this.order) return;
    this.api.cancelOrder(this.order.id).subscribe(o => this.order = o);
  }
}
