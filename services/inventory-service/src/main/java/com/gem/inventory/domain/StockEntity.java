package com.gem.inventory.domain;

import jakarta.persistence.*;

@Entity @Table(name="stock")
public class StockEntity {
  @Id @Column(length=50)
  private String sku;

  @Column(nullable=false) private int available;
  @Column(nullable=false) private int reserved;

  public StockEntity(){}
  public StockEntity(String sku,int available,int reserved){this.sku=sku;this.available=available;this.reserved=reserved;}

  public String getSku(){return sku;}
  public int getAvailable(){return available;}
  public int getReserved(){return reserved;}

  public void reserve(int qty){
    if (available < qty) throw new IllegalStateException("OUT_OF_STOCK");
    available -= qty;
    reserved += qty;
  }
}
