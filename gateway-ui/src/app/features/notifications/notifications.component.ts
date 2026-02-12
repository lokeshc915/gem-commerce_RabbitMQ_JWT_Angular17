import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

type Note = { id: number; title: string; message: string; createdAt: string };

@Component({
  standalone: true,
  template: `
  <h2>Notifications</h2>
  <div class="card"><button class="btn" (click)="load()">Reload</button></div>
  <div class="card" *ngFor="let n of notes">
    <strong>{{n.title}}</strong>
    <div style="margin-top:6px">{{n.message}}</div>
    <div style="font-size:12px;color:#555;margin-top:6px">{{n.createdAt}}</div>
  </div>
  `
})
export class NotificationsComponent {
  notes: Note[] = [];
  constructor(private http: HttpClient) { this.load(); }
  load(){ this.http.get<Note[]>('http://localhost:8080/api/proxy/notifications').subscribe(x => this.notes = x); }
}
