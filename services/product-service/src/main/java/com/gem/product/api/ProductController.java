package com.gem.product.api;

import com.gem.product.domain.ProductEntity;
import com.gem.product.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductRepository repo;
  public ProductController(ProductRepository repo){this.repo=repo;}
  @GetMapping public List<ProductEntity> list(){ return repo.findAll(); }
}
