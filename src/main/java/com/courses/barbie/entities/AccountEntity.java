package com.courses.barbie.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class AccountEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 16)
    private String name;

    @Column(name = "balance")
    private int balance;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accounts_clothes",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "clothes_id"))
    private List<ClothesEntity> accountClothes = new ArrayList<>();

    public void addClothes(ClothesEntity someClothes) {
        accountClothes.add(someClothes);
        someClothes.getClothesAccounts().add(this);
    }
}
