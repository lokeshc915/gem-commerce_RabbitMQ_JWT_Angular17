package com.gem.common.messaging;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitTopology {

  @Bean
  public TopicExchange domainExchange(@Value("${gem.rabbitmq.exchange}") String exchange) {
    return new TopicExchange(exchange, true, false);
  }

  @Bean
  public Queue inventoryQueue(@Value("${gem.rabbitmq.inventory-queue}") String name) {
    return QueueBuilder.durable(name).build();
  }

  @Bean
  public Queue notificationQueue(@Value("${gem.rabbitmq.notification-queue}") String name) {
    return QueueBuilder.durable(name).build();
  }

  @Bean
  public Binding inventoryBinding(TopicExchange domainExchange, Queue inventoryQueue) {
    return BindingBuilder.bind(inventoryQueue).to(domainExchange).with("order.*");
  }

  @Bean
  public Binding notificationBinding(TopicExchange domainExchange, Queue notificationQueue) {
    return BindingBuilder.bind(notificationQueue).to(domainExchange).with("#");
  }
}
