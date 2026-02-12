import { ResolveFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { OrderApiService } from './order-api.service';
import { catchError, of } from 'rxjs';

export const orderDetailResolver: ResolveFn<any> = (route) => {
  const api = inject(OrderApiService);
  const router = inject(Router);
  const id = route.paramMap.get('id')!;
  return api.getOrderById(id).pipe(
    catchError(() => { router.navigate(['/orders']); return of(null); })
  );
};
