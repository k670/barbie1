package com.courses.barbie.dao;

import com.courses.barbie.entities.ClothesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothesDAO extends CrudRepository<ClothesEntity, Integer> {
}
