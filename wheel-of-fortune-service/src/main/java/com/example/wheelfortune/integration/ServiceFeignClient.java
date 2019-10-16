package com.example.wheelfortune.integration;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface ServiceFeignClient {
    @PostMapping(path = "/account",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateBalance(@RequestBody AccountBalanceDiffDTO userEntity);

    @PostMapping(value = "/account/clothes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addClothes(@RequestBody ClothesDTO clothesDTO);
}