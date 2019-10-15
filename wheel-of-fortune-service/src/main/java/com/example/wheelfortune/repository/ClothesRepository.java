package com.example.wheelfortune.repository;

import java.util.Collection;

import com.example.wheelfortune.repository.models.ClothesModel;
import org.springframework.data.repository.CrudRepository;

public interface ClothesRepository extends CrudRepository<ClothesModel, Integer> {
    Collection<ClothesModel> findByShopName(String shopName);
}
