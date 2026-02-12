package com.gem.notify.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity @Table(name="notifications")
public class NotificationEntity {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false) private String title;
  @Column(nullable=false, length=2000) private String message;
  @Column(nullable=false) private Instant createdAt;

  public NotificationEntity(){}
  public NotificationEntity(String title,String message,Instant createdAt){this.title=title;this.message=message;this.createdAt=createdAt;}

  public Long getId(){return id;}
  public String getTitle(){return title;}
  public String getMessage(){return message;}
  public Instant getCreatedAt(){return createdAt;}
}
