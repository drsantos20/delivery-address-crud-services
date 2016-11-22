package com.customer.addresscrudservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
 
@SpringBootApplication
@ComponentScan(basePackages = "com.customer.addresscrudservice")
public class AddressCrudServiceApplication {
     
    public static void main(String[] args) {
       SpringApplication.run(AddressCrudServiceApplication.class, args);
    }
     
    @Bean
    public RestTemplate geRestTemplate() {
        return new RestTemplate();
    }
}