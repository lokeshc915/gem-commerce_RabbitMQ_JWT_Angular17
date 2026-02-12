package com.gem.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gem.product","com.gem.common"})
public class ProductServiceApplication { public static void main(String[] args){SpringApplication.run(ProductServiceApplication.class,args);} }
