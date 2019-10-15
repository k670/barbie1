package com.courses.barbie.dao;

import com.courses.barbie.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends CrudRepository<AccountEntity, Integer> {
}
