package com.gem.notify.api;

import com.gem.notify.domain.NotificationEntity;
import com.gem.notify.repo.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
  private final NotificationRepository repo;
  public NotificationController(NotificationRepository repo){this.repo=repo;}
  @GetMapping public List<NotificationEntity> list(){ return repo.findAll().stream().sorted((a,b)->b.getId().compareTo(a.getId())).toList(); }
}
