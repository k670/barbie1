package com.example.wheelfortune.service;

import java.util.Optional;

import com.example.wheelfortune.repository.models.WheelFortuneModel;
import com.example.wheelfortune.repository.WheelFortuneRepository;
import com.example.wheelfortune.web.WheelFortuneStatusModel;
import org.springframework.stereotype.Service;

@Service
public class BonusService {

    private long price = 100;
    private WheelFortuneRepository wheelFortuneRepository;

    public BonusService(WheelFortuneRepository wheelFortuneRepository) {
        this.wheelFortuneRepository = wheelFortuneRepository;
    }

    public String generateWinClothes(long userId) {
        //clothes in this shop == user clothes in this shop
        //update user balance
        long timeNow = System.currentTimeMillis();

        return "Win";
    }

    public WheelFortuneStatusModel getPrice(long userId) {
        long timeNow = System.currentTimeMillis();
        Optional<WheelFortuneModel> optionalWheelFortuneModel = wheelFortuneRepository.findById(userId);
        if (optionalWheelFortuneModel.isPresent()) {
            long deltaTime = timeNow - optionalWheelFortuneModel.get().getTimeLastSpin();
            long timeForOneDay = 1000 * 60 * 60 * 24;
            if (deltaTime / timeForOneDay > 0) {
                return WheelFortuneStatusModel.builder().timeToWait(timeForOneDay - deltaTime).price(0).build();
            }
        }
        return WheelFortuneStatusModel.builder().price(price).timeToWait(0).build();
    }
}
