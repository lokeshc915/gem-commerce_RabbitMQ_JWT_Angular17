package com.gem.inventory.config;

import com.gem.inventory.domain.StockEntity;
import com.gem.inventory.repo.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DataSeeder {
  @Bean
  CommandLineRunner seed(StockRepository repo){
    return args -> {
      if (repo.count() == 0) {
        repo.save(new StockEntity("SKU-1", 10, 0));
        repo.save(new StockEntity("SKU-2", 5, 0));
        repo.save(new StockEntity("SKU-3", 2, 0));
      }
    };
  }
}
