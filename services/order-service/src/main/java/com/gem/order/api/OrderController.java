package com.gem.order.api;

import com.gem.common.events.OrderCreatedEvent;
import com.gem.order.domain.OrderEntity;
import com.gem.order.service.OrderAppService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderAppService svc;
  public OrderController(OrderAppService svc){this.svc=svc;}

  public record CreateOrderRequest(@NotEmpty List<Line> lines){
    public record Line(String sku, int qty){}
  }

  @GetMapping public List<OrderEntity> list(){ return svc.list(); }
  @GetMapping("/{id}") public OrderEntity get(@PathVariable String id){ return svc.get(id); }

  @PostMapping public OrderEntity create(@RequestBody @Valid CreateOrderRequest req){
    List<OrderCreatedEvent.OrderLine> lines = req.lines().stream().map(l -> new OrderCreatedEvent.OrderLine(l.sku(), l.qty())).toList();
    return svc.create(lines);
  }

  @PostMapping("/{id}/cancel") public OrderEntity cancel(@PathVariable String id){ return svc.cancel(id); }
}
