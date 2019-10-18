package com.example.wheelfortune.repository;

import java.util.List;

import com.example.wheelfortune.repository.models.ClothesEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClothesRepository extends CrudRepository<ClothesEntity, Integer> {
     List<ClothesEntity> findAll();
}
