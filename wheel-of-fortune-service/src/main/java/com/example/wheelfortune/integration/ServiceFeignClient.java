package com.example.wheelfortune.integration;


import com.example.wheelfortune.service.AccountBalanceDiffDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface ServiceFeignClient {
    @PutMapping("/account")
    public void updateBalance(@RequestBody AccountBalanceDiffDTO userEntity);

}