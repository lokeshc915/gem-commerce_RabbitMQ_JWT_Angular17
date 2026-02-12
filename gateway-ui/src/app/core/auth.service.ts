import { Injectable, computed, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

type LoginResponse = { token: string; username: string; roles: string[] };

@Injectable({ providedIn: 'root' })
export class AuthService {
  private _token = signal<string | null>(localStorage.getItem('token'));
  private _username = signal<string>(localStorage.getItem('username') ?? '');
  private _roles = signal<string[]>(JSON.parse(localStorage.getItem('roles') ?? '[]'));

  token = computed(() => this._token());
  username = computed(() => this._username());
  roles = computed(() => this._roles());

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string) {
    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/login', { username, password });
  }

  setSession(resp: LoginResponse) {
    localStorage.setItem('token', resp.token);
    localStorage.setItem('username', resp.username);
    localStorage.setItem('roles', JSON.stringify(resp.roles));
    this._token.set(resp.token);
    this._username.set(resp.username);
    this._roles.set(resp.roles);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('username');
    localStorage.removeItem('roles');
    this._token.set(null);
    this._username.set('');
    this._roles.set([]);
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean { return !!this._token(); }
  hasRole(role: string): boolean { return this._roles().includes(role); }
}
