package com.example.wheelfortune.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.example.wheelfortune.integration.AccountBalanceDiffDTO;
import com.example.wheelfortune.integration.ClothesDTO;
import com.example.wheelfortune.integration.ServiceFeignClient;
import com.example.wheelfortune.repository.ClothesRepository;
import com.example.wheelfortune.repository.WheelFortuneRepository;
import com.example.wheelfortune.repository.models.AccountEntity;
import com.example.wheelfortune.repository.models.ClothesEntity;
import com.example.wheelfortune.repository.models.WheelFortuneModel;
import com.example.wheelfortune.web.WheelFortuneStatusModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BonusService {

    @Value("${spin.price}")
    private int price;
    private WheelFortuneRepository wheelFortuneRepository;
    private ServiceFeignClient serviceFeignClient;
    private ClothesRepository clothesRepository;

    public BonusService(WheelFortuneRepository wheelFortuneRepository, ServiceFeignClient serviceFeignClient, ClothesRepository clothesRepository) {
        this.wheelFortuneRepository = wheelFortuneRepository;
        this.serviceFeignClient = serviceFeignClient;
        this.clothesRepository = clothesRepository;
    }

    public String generateWinClothes(int userId) {

        List<ClothesEntity> repositoryAll = clothesRepository.findAll();

        long count = repositoryAll.size();
        if (count == 0) {
            return "Error";
        }

        int winIndex = new Random().nextInt(Long.valueOf(count).intValue()) ;
        ClothesEntity clothesEntity = repositoryAll.get(winIndex);


        serviceFeignClient.addClothes(new ClothesDTO(clothesEntity.getId(), userId));
        serviceFeignClient.updateBalance(AccountBalanceDiffDTO.builder().accountId(userId).balanceDifference(0 - price).build());
        wheelFortuneRepository.save(new WheelFortuneModel(userId, System.currentTimeMillis()));
        return clothesEntity.getName();
    }

    public WheelFortuneStatusModel getPrice(long userId) {
        long timeNow = System.currentTimeMillis();
        Optional<WheelFortuneModel> optionalWheelFortuneModel = wheelFortuneRepository.findById(userId);
        if (optionalWheelFortuneModel.isPresent()) {
            long deltaTime = timeNow - optionalWheelFortuneModel.get().getTimeLastSpin();
            long timeForOneDay = 1000 * 60 * 60 * 24;
            if ((1.0 * deltaTime) / timeForOneDay > 0.0) {
                return WheelFortuneStatusModel.builder().timeToWait(timeForOneDay - deltaTime).price(price).enoughMoney(false).build();
            }
        }
        AccountEntity accountEntity = serviceFeignClient.getAccountById(Long.valueOf(userId).intValue());
        boolean enough = price <= accountEntity.getBalance();
        return WheelFortuneStatusModel.builder().timeToWait(0).price(price).enoughMoney(enough).build();
    }
}
