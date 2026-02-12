package com.gem.inventory.api;

import com.gem.inventory.domain.StockEntity;
import com.gem.inventory.repo.StockRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
  private final StockRepository repo;
  public InventoryController(StockRepository repo){this.repo=repo;}

  @GetMapping("/stock")
  public List<StockEntity> stock(){ return repo.findAll(); }
}
