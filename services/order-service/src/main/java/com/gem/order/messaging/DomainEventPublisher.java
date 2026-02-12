package com.gem.order.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gem.common.events.DomainEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher {
  private final RabbitTemplate rabbit;
  private final ObjectMapper mapper;
  private final String exchange;

  public DomainEventPublisher(RabbitTemplate rabbit, ObjectMapper mapper, @Value("${gem.rabbitmq.exchange}") String exchange) {
    this.rabbit=rabbit; this.mapper=mapper; this.exchange=exchange;
  }

  public void publish(DomainEvent event) {
    try {
      rabbit.convertAndSend(exchange, event.eventType(), mapper.writeValueAsString(event));
    } catch (Exception e) {
      throw new RuntimeException("publish failed", e);
    }
  }
}
