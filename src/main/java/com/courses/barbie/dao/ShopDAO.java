package com.courses.barbie.dao;

import com.courses.barbie.entities.ShopEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDAO extends CrudRepository<ShopEntity, Integer> {
}

