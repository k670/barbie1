package com.example.wheelfortune;

import com.example.wheelfortune.integration.ServiceFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = {ServiceFeignClient.class})
@EnableDiscoveryClient
public class WheelfortuneApplication {

    public static void main(String[] args) {
        SpringApplication.run(WheelfortuneApplication.class, args);
    }

}
