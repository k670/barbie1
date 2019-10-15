package com.example.wheelfortune.service;

import java.util.Optional;
import java.util.Random;

import com.example.wheelfortune.integration.ServiceFeignClient;
import com.example.wheelfortune.repository.ClothesRepository;
import com.example.wheelfortune.repository.WheelFortuneRepository;
import com.example.wheelfortune.repository.models.ClothesEntity;
import com.example.wheelfortune.repository.models.WheelFortuneModel;
import com.example.wheelfortune.web.WheelFortuneStatusModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
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

        serviceFeignClient.updateBalance(AccountBalanceDiffDTO.builder().accountId(userId).balanceDifference(0-price).build());
        wheelFortuneRepository.save(new WheelFortuneModel(userId,System.currentTimeMillis()));

        long count = clothesRepository.count();
        int winIndex = new Random().nextInt(Long.valueOf(count).intValue());
        Optional<ClothesEntity> clothesEntity = clothesRepository.findById(winIndex);
        if(clothesEntity.isPresent()){
            return clothesEntity.get().getName();
        }
        return "Error";
    }

    public WheelFortuneStatusModel getPrice(long userId) {
        long timeNow = System.currentTimeMillis();
        Optional<WheelFortuneModel> optionalWheelFortuneModel = wheelFortuneRepository.findById(userId);
        if (optionalWheelFortuneModel.isPresent()) {
            long deltaTime = timeNow - optionalWheelFortuneModel.get().getTimeLastSpin();
            long timeForOneDay = 1000 * 60 * 60 * 24;
            if ((1.0*deltaTime) / timeForOneDay > 0.0) {
                return WheelFortuneStatusModel.builder().timeToWait(timeForOneDay - deltaTime).price(price).build();
            }
        }
        return WheelFortuneStatusModel.builder().timeToWait(0).price(price).build();
    }
}
