package com.courses.barbie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BarbieApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarbieApplication.class, args);
    }

}
