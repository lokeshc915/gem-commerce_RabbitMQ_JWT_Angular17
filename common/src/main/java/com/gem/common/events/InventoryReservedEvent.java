package com.gem.common.events;

import java.time.Instant;

public record InventoryReservedEvent(String orderId, boolean success, String reason, Instant occurredAt) implements DomainEvent {
  @Override public String eventType() { return success ? "inventory.reserved" : "inventory.rejected"; }
}
