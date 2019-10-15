package com.example.wheelfortune.repository.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "shops")
@Data
public class ShopEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shops_clothes",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "clothes_id"))
    private List<ClothesEntity> shopClothes = new ArrayList<>();

    public void addClothes(ClothesEntity someClothes){
        shopClothes.add(someClothes);
        someClothes.getClothesShops().add(this);
    }
}
