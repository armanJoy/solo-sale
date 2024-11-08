package com.test.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing
public class SoloSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoloSaleApplication.class, args);
        System.out.println("Solo Sale Application Started...");
    }
}