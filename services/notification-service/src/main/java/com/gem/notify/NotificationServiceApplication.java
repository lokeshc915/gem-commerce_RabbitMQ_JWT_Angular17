package com.gem.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gem.notify","com.gem.common"})
public class NotificationServiceApplication { public static void main(String[] args){SpringApplication.run(NotificationServiceApplication.class,args);} }
