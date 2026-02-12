package com.gem.order.domain;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity @Table(name="orders")
public class OrderEntity {
  @Id @Column(length=40)
  private String id;

  @Column(nullable=false)
  private String status;

  @Column(nullable=false)
  private Instant createdAt;

  @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  private List<OrderLineEntity> lines = new ArrayList<>();

  public OrderEntity() {}
  public OrderEntity(String id, String status, Instant createdAt){ this.id=id; this.status=status; this.createdAt=createdAt; }

  public String getId(){ return id; }
  public String getStatus(){ return status; }
  public void setStatus(String status){ this.status=status; }
  public Instant getCreatedAt(){ return createdAt; }
  public List<OrderLineEntity> getLines(){ return lines; }

  public void addLine(OrderLineEntity l){ l.setOrder(this); lines.add(l); }
}
