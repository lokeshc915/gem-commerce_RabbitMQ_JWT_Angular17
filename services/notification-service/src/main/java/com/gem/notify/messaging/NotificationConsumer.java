package com.gem.notify.messaging;

import com.gem.notify.domain.NotificationEntity;
import com.gem.notify.repo.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class NotificationConsumer {
  private final NotificationRepository repo;
  public NotificationConsumer(NotificationRepository repo){this.repo=repo;}

  @RabbitListener(queues = "${gem.rabbitmq.notification-queue}")
  public void onEvent(String payload) {
    String title = "Domain Event";
    if (payload.contains("order.created")) title = "Order created";
    if (payload.contains("inventory.reserved")) title = "Inventory reserved";
    if (payload.contains("inventory.rejected")) title = "Inventory rejected";
    repo.save(new NotificationEntity(title, payload, Instant.now()));
  }
}
