package com.example.wheelfortune.repository.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ClothesModel {
    @Id
    private int id;

    private String shopName;

    private String clothesName;

    private int price;

}
