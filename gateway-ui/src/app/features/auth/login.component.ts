import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../core/auth.service';
import { Router } from '@angular/router';

@Component({
  standalone: true,
  imports: [FormsModule],
  template: `
  <div style="max-width:420px;margin:60px auto" class="card">
    <h2>Login</h2>
    <p>Use <code>admin/admin123</code> or <code>user/user123</code>.</p>
    <div style="display:grid;gap:10px">
      <label>Username <input [(ngModel)]="username" /></label>
      <label>Password <input [(ngModel)]="password" type="password" /></label>
      <button class="btn" (click)="doLogin()">Login</button>
      <div *ngIf="error" style="color:#b00020">{{error}}</div>
    </div>
  </div>
  `
})
export class LoginComponent {
  username = 'admin';
  password = 'admin123';
  error = '';
  constructor(private auth: AuthService, private router: Router) {}
  doLogin() {
    this.error='';
    this.auth.login(this.username, this.password).subscribe({
      next: resp => { this.auth.setSession(resp); this.router.navigate(['/']); },
      error: () => this.error='Invalid credentials'
    });
  }
}
