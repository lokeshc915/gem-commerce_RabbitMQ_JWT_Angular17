package com.gem.order.domain;

import jakarta.persistence.*;

@Entity @Table(name="order_lines")
public class OrderLineEntity {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false)
  private OrderEntity order;

  @Column(nullable=false) private String sku;
  @Column(nullable=false) private int qty;

  public OrderLineEntity(){}
  public OrderLineEntity(String sku,int qty){this.sku=sku;this.qty=qty;}

  public Long getId(){return id;}
  public OrderEntity getOrder(){return order;}
  public void setOrder(OrderEntity order){this.order=order;}
  public String getSku(){return sku;}
  public int getQty(){return qty;}
}
