package com.courses.barbie.entities;

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
