package com.courses.barbie.init;

import com.courses.barbie.dao.AccountDAO;
import com.courses.barbie.dao.ClothesDAO;
import com.courses.barbie.dao.ShopDAO;
import com.courses.barbie.entities.AccountEntity;
import com.courses.barbie.entities.ClothesEntity;
import com.courses.barbie.entities.ShopEntity;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {
    private AccountDAO accountDAO;
    private ShopDAO shopDAO;
    private ClothesDAO clothesDAO;

    public DataInit(AccountDAO accountDAO, ShopDAO shopDAO, ClothesDAO clothesDAO) {
        this.accountDAO = accountDAO;
        this.shopDAO = shopDAO;
        this.clothesDAO = clothesDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AccountEntity account = new AccountEntity();
        account.setName("Alisa");
        account.setBalance(228);

        AccountEntity account2 = new AccountEntity();
        account2.setName("Ben");
        account2.setBalance(1337);

        ShopEntity shop = new ShopEntity();
        shop.setName("Secret");

        ClothesEntity redDress = new ClothesEntity();
        redDress.setName("Red dress");

        shop.addClothes(redDress);

        account.addClothes(redDress);
        account2.addClothes(redDress);

        clothesDAO.save(redDress);
        shopDAO.save(shop);
        accountDAO.save(account);
        accountDAO.save(account2);


        for (int i = 1; i < 3; i++) {
            ClothesEntity clothesEntity1 = new ClothesEntity();
            clothesEntity1.setName("body_"+i+"a");
            clothesDAO.save(clothesEntity1);
            ClothesEntity clothesEntity2 = new ClothesEntity();
            clothesEntity2.setName("body_"+i+"b");
            clothesDAO.save(clothesEntity2);
            ClothesEntity clothesEntity3 = new ClothesEntity();
            clothesEntity3.setName("body_"+i+"c");
            clothesDAO.save(clothesEntity3);
        }
        int i = 1;
        ClothesEntity clothesEntity1 = new ClothesEntity();
        clothesEntity1.setName("shoes_"+i+"a");
        clothesDAO.save(clothesEntity1);
        ClothesEntity clothesEntity2 = new ClothesEntity();
        clothesEntity2.setName("shoes_"+i+"b");
        clothesDAO.save(clothesEntity2);
        ClothesEntity clothesEntity3 = new ClothesEntity();
        clothesEntity3.setName("shoes_"+i+"c");
        clothesDAO.save(clothesEntity3);
    }
}
