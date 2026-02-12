import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export type Order = { id: string; status: string; createdAt: string; lines: { sku: string; qty: number }[] };

@Injectable({ providedIn: 'root' })
export class OrderApiService {
  constructor(private http: HttpClient) {}
  listOrders() { return this.http.get<Order[]>('http://localhost:8080/api/proxy/orders'); }
  getOrderById(id: string) { return this.http.get<Order>(`http://localhost:8080/api/proxy/orders/${id}`); }
  createOrder(payload: { lines: { sku: string; qty: number }[] }) { return this.http.post<Order>('http://localhost:8080/api/proxy/orders', payload); }
  cancelOrder(id: string) { return this.http.post<Order>(`http://localhost:8080/api/proxy/orders/${id}/cancel`, {}); }
}
