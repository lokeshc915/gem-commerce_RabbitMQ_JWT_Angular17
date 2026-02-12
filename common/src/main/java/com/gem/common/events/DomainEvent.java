package com.gem.common.events;

import java.time.Instant;

public interface DomainEvent { String eventType(); Instant occurredAt(); }
