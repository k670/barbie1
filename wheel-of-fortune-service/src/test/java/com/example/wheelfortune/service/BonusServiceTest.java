package com.example.wheelfortune.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.wheelfortune.integration.ClothesDTO;
import com.example.wheelfortune.integration.ServiceFeignClient;
import com.example.wheelfortune.repository.ClothesRepository;
import com.example.wheelfortune.repository.WheelFortuneRepository;
import com.example.wheelfortune.repository.models.AccountEntity;
import com.example.wheelfortune.repository.models.ClothesEntity;
import com.example.wheelfortune.repository.models.WheelFortuneModel;
import com.example.wheelfortune.web.WheelFortuneStatusModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BonusServiceTest {

    @InjectMocks
    public BonusService bonusService;


    @Mock
    private WheelFortuneRepository wheelFortuneRepository;
    @Mock
    private ServiceFeignClient serviceFeignClient;
    @Mock
    private ClothesRepository clothesRepository;

    @Test
    public void test_getPrice_priceMoreBalanceAndFirstSpin(){
        long userId = 1;
        int balance = 10;
        int price = 100;

        when(wheelFortuneRepository.findById(userId)).thenReturn(Optional.empty());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(balance);
        when (serviceFeignClient.getAccountById((int)userId)).thenReturn(accountEntity);

        ReflectionTestUtils.setField(bonusService, "price", price);
        WheelFortuneStatusModel expected = WheelFortuneStatusModel.builder().enoughMoney(false).price(price).timeToWait(0).build();

        WheelFortuneStatusModel actual = bonusService.getPrice(userId);

        Assert.assertEquals(expected, actual);

    }
    @Test
    public void test_getPrice_balanceMorePriceAndFirstSpin(){
        long userId = 1;
        int balance = 100;
        int price = 10;

        when(wheelFortuneRepository.findById(userId)).thenReturn(Optional.empty());
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(balance);
        when (serviceFeignClient.getAccountById((int)userId)).thenReturn(accountEntity);

        ReflectionTestUtils.setField(bonusService, "price", price);
        WheelFortuneStatusModel expected = WheelFortuneStatusModel.builder().enoughMoney(true).price(price).timeToWait(0).build();

        WheelFortuneStatusModel actual = bonusService.getPrice(userId);

        Assert.assertEquals(expected, actual);

    }
    @Test
    public void test_getPrice_secondSpinAndNeedToWait(){
        long userId = 1;
        int balance = 100;
        int price = 10;
        int timeToLastSpin = 90;

        WheelFortuneModel wheelFortuneModel  = new WheelFortuneModel(userId,timeToLastSpin);
        when(wheelFortuneRepository.findById(userId)).thenReturn(Optional.of(wheelFortuneModel));
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(balance);
        when (serviceFeignClient.getAccountById((int)userId)).thenReturn(accountEntity);

        ReflectionTestUtils.setField(bonusService, "price", price);
        WheelFortuneStatusModel expected = WheelFortuneStatusModel.builder().enoughMoney(false).price(price).timeToWait(timeToLastSpin).build();

        WheelFortuneStatusModel actual = bonusService.getPrice(userId);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void test_generateWinClothes(){
        int userId = 1;
        String clothesName = UUID.randomUUID().toString();
        ClothesEntity clothesEntity = new ClothesEntity();
        clothesEntity.setName(clothesName);
        List<ClothesEntity> list = new ArrayList<>();
        list.add(clothesEntity);
        when(clothesRepository.findAll()).thenReturn(list);
        ClothesDTO clothesDTO = new ClothesDTO(clothesEntity.getId(),userId);

        String actual = bonusService.generateWinClothes(userId);

        Assert.assertEquals(clothesName, actual);

        verify(wheelFortuneRepository).save(any());
        verify(serviceFeignClient).addClothes(clothesDTO);
        verify(serviceFeignClient).updateBalance(any());

    }
}
