package com.gem.common.events;

import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(String orderId, List<OrderLine> lines, Instant occurredAt) implements DomainEvent {
  @Override public String eventType() { return "order.created"; }
  public record OrderLine(String sku, int qty) {}
}
