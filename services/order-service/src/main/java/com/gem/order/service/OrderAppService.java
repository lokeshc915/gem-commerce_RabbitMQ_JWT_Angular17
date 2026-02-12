package com.gem.order.service;

import com.gem.common.events.OrderCreatedEvent;
import com.gem.order.domain.*;
import com.gem.order.messaging.DomainEventPublisher;
import com.gem.order.repo.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class OrderAppService {
  private final OrderRepository repo;
  private final DomainEventPublisher publisher;

  public OrderAppService(OrderRepository repo, DomainEventPublisher publisher){this.repo=repo;this.publisher=publisher;}

  @Transactional
  public OrderEntity create(List<OrderCreatedEvent.OrderLine> lines) {
    String id = UUID.randomUUID().toString();
    OrderEntity o = new OrderEntity(id, "CREATED", Instant.now());
    for (var l: lines) o.addLine(new OrderLineEntity(l.sku(), l.qty()));
    repo.save(o);
    publisher.publish(new OrderCreatedEvent(id, lines, Instant.now()));
    return o;
  }

  public List<OrderEntity> list(){ return repo.findAll(); }
  public OrderEntity get(String id){ return repo.findById(id).orElseThrow(); }

  @Transactional
  public OrderEntity cancel(String id){
    OrderEntity o = repo.findById(id).orElseThrow();
    o.setStatus("CANCELLED");
    return o;
  }
}
