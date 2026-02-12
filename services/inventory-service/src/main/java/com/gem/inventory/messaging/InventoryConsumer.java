package com.gem.inventory.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gem.common.events.InventoryReservedEvent;
import com.gem.common.events.OrderCreatedEvent;
import com.gem.inventory.repo.StockRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class InventoryConsumer {
  private final ObjectMapper mapper;
  private final StockRepository repo;
  private final InventoryPublisher publisher;

  public InventoryConsumer(ObjectMapper mapper, StockRepository repo, InventoryPublisher publisher){
    this.mapper=mapper; this.repo=repo; this.publisher=publisher;
  }

  @RabbitListener(queues = "${gem.rabbitmq.inventory-queue}")
  public void onMessage(String payload) throws Exception {
    if (!payload.contains(""orderId"")) return;
    OrderCreatedEvent evt = mapper.readValue(payload, OrderCreatedEvent.class);

    boolean ok = true;
    String reason = "OK";
    try {
      for (var line : evt.lines()) {
        var stock = repo.findById(line.sku()).orElseThrow();
        stock.reserve(line.qty());
        repo.save(stock);
      }
    } catch (Exception ex) {
      ok = false;
      reason = ex.getMessage();
    }

    publisher.publish(new InventoryReservedEvent(evt.orderId(), ok, reason, Instant.now()));
  }
}
