package com.gem.product.config;

import com.gem.product.domain.ProductEntity;
import com.gem.product.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DataSeeder {
  @Bean CommandLineRunner seed(ProductRepository repo){
    return args -> {
      if (repo.count()==0){
        repo.save(new ProductEntity("SKU-1","Laptop Sleeve",29.99));
        repo.save(new ProductEntity("SKU-2","USB-C Hub",49.99));
        repo.save(new ProductEntity("SKU-3","Wireless Mouse",19.99));
      }
    };
  }
}
