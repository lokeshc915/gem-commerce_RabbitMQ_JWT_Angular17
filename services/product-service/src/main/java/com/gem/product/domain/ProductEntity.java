package com.gem.product.domain;

import jakarta.persistence.*;

@Entity @Table(name="products")
public class ProductEntity {
  @Id @Column(length=50)
  private String sku;

  @Column(nullable=false) private String name;
  @Column(nullable=false) private double price;

  public ProductEntity(){}
  public ProductEntity(String sku,String name,double price){this.sku=sku;this.name=name;this.price=price;}

  public String getSku(){return sku;}
  public String getName(){return name;}
  public double getPrice(){return price;}
}
