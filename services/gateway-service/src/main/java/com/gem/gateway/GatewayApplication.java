package com.gem.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gem.gateway","com.gem.common"})
public class GatewayApplication { public static void main(String[] args){SpringApplication.run(GatewayApplication.class,args);} }
