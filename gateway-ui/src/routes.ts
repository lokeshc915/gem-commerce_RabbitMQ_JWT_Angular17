import { Routes } from '@angular/router';
import { LoginComponent } from './app/features/auth/login.component';
import { ShellComponent } from './app/layout/shell.component';
import { authGuard } from './app/core/auth.guard';
import { OrdersListComponent } from './app/features/orders/orders-list.component';
import { OrderDetailComponent } from './app/features/orders/order-detail.component';
import { orderDetailResolver } from './app/features/orders/order-detail.resolver';
import { InventoryComponent } from './app/features/inventory/inventory.component';
import { ProductsComponent } from './app/features/products/products.component';
import { NotificationsComponent } from './app/features/notifications/notifications.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: '',
    component: ShellComponent,
    canActivate: [authGuard],
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'orders' },
      { path: 'orders', component: OrdersListComponent },
      { path: 'orders/:id', component: OrderDetailComponent, resolve: { order: orderDetailResolver } },
      { path: 'inventory', component: InventoryComponent },
      { path: 'products', component: ProductsComponent },
      { path: 'notifications', component: NotificationsComponent },
    ],
  },
  { path: '**', redirectTo: '' },
];
