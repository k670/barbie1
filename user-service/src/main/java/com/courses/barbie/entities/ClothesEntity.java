package com.courses.barbie.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clothes")
@Data
public class ClothesEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "shopClothes", fetch = FetchType.LAZY)
    private List<ShopEntity> clothesShops = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "accountClothes", fetch = FetchType.LAZY)
    private List<AccountEntity> clothesAccounts = new ArrayList<>();

    public void addShop(ShopEntity shop){
        clothesShops.add(shop);
        shop.getShopClothes().add(this);
    }

    public void addAccount(AccountEntity account){
        clothesAccounts.add(account);
        account.getAccountClothes().add(this);
    }
}
