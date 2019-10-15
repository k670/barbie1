package com.example.wheelfortune.repository.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

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

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @ManyToMany(mappedBy = "shopClothes", fetch = FetchType.LAZY)
    private List<ShopEntity> clothesShops = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "accountClothes", fetch = FetchType.LAZY)
    private List<AccountEntity> clothesAccounts = new ArrayList<>();

}
