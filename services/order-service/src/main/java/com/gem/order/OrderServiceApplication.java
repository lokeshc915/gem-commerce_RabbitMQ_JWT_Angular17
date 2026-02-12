package com.gem.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gem.order","com.gem.common"})
public class OrderServiceApplication { public static void main(String[] args){SpringApplication.run(OrderServiceApplication.class,args);} }
