package com.gem.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gem.inventory","com.gem.common"})
public class InventoryServiceApplication { public static void main(String[] args){SpringApplication.run(InventoryServiceApplication.class,args);} }
