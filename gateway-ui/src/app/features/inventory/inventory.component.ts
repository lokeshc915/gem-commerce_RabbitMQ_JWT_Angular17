import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type Stock = { sku: string; available: number; reserved: number };

@Component({
  standalone: true,
  template: `
  <h2>Inventory</h2>
  <div class="card"><button class="btn" (click)="load()">Reload</button></div>
  <div class="card" *ngFor="let s of stocks">
    <strong>{{s.sku}}</strong>
    <div>Available: {{s.available}}</div>
    <div>Reserved: {{s.reserved}}</div>
  </div>
  `
})
export class InventoryComponent {
  stocks: Stock[] = [];
  constructor(private http: HttpClient) { this.load(); }
  load(){ this.http.get<Stock[]>('http://localhost:8080/api/proxy/inventory/stock').subscribe(x => this.stocks = x); }
}
