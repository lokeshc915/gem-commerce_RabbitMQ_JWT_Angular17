import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type Product = { sku: string; name: string; price: number };

@Component({
  standalone: true,
  template: `
  <h2>Products</h2>
  <div class="card"><button class="btn" (click)="load()">Reload</button></div>
  <div class="card" *ngFor="let p of products">
    <strong>{{p.name}}</strong>
    <div>SKU: {{p.sku}}</div>
    <div>Price: {{p.price}}</div>
  </div>
  `
})
export class ProductsComponent {
  products: Product[] = [];
  constructor(private http: HttpClient) { this.load(); }
  load(){ this.http.get<Product[]>('http://localhost:8080/api/proxy/products').subscribe(x => this.products = x); }
}
