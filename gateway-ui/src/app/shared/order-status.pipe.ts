import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'orderStatus', standalone: true })
export class OrderStatusPipe implements PipeTransform {
  transform(value: string | null | undefined): string {
    switch (value) {
      case 'CREATED': return 'Created';
      case 'CONFIRMED': return 'Confirmed';
      case 'CANCELLED': return 'Cancelled';
      case 'FAILED': return 'Failed';
      default: return value ?? '';
    }
  }
}
