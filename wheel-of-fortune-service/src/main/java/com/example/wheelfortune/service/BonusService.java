package com.example.wheelfortune.service;

import java.util.Optional;

import com.example.wheelfortune.repository.models.WheelFortuneModel;
import com.example.wheelfortune.repository.WheelFortuneRepository;
import com.example.wheelfortune.web.WheelFortuneStatusModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BonusService {

    @Value("${spin.price}")
    private long price;
    private WheelFortuneRepository wheelFortuneRepository;

    public BonusService(WheelFortuneRepository wheelFortuneRepository) {
        this.wheelFortuneRepository = wheelFortuneRepository;
    }

    public String generateWinClothes(long userId) {

        //update user balance

        return "Win";
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
