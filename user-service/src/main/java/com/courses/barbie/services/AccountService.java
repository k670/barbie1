package com.courses.barbie.services;

import java.util.Optional;

import com.courses.barbie.AccountBalanceDiffDTO;
import com.courses.barbie.controllers.ClothesDTO;
import com.courses.barbie.dao.AccountDAO;
import com.courses.barbie.dao.ClothesDAO;
import com.courses.barbie.entities.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountDAO accountDAO;
    private final ClothesDAO clothesDAO;

    public AccountService(AccountDAO accountDAO, ClothesDAO clothesDAO) {
        this.accountDAO = accountDAO;
        this.clothesDAO = clothesDAO;
    }

    public AccountEntity getAccountById(int accountId) {
        Optional<AccountEntity> accountOptional = accountDAO.findById(accountId);
        return accountOptional.orElse(null);
    }

    public void changeAccountBalance(AccountBalanceDiffDTO accountBalanceDiff) {
        Optional<AccountEntity> accountOptional = accountDAO.findById(accountBalanceDiff.getAccountId());
        if (accountOptional.isPresent()) {
            AccountEntity accountEntity = accountOptional.get();
            accountEntity.setBalance(accountEntity.getBalance() + accountBalanceDiff.getBalanceDifference());
            accountDAO.save(accountEntity);
        }
    }

    public Iterable<AccountEntity> getAllAccounts() {
        return accountDAO.findAll();
    }

    public void addClothes(ClothesDTO clothesDTO) {
        Optional<AccountEntity> accountOptional = accountDAO.findById(clothesDTO.getAccountId());
        if (accountOptional.isPresent()) {
            AccountEntity accountEntity = accountOptional.get();
            clothesDAO.findById(clothesDTO.clothesId).ifPresent(accountEntity::addClothes);
            accountDAO.save(accountEntity);
        }
    }
}
