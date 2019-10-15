package com.courses.barbie.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
