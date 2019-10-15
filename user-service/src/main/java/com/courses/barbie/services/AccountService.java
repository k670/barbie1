package com.courses.barbie.services;

import com.courses.barbie.AccountBalanceDiffDTO;
import com.courses.barbie.dao.AccountDAO;
import com.courses.barbie.entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

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
}
