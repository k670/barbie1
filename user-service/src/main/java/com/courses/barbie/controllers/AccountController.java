package com.courses.barbie.controllers;

import com.courses.barbie.AccountBalanceDiffDTO;
import com.courses.barbie.dao.AccountDAO;
import com.courses.barbie.dao.ClothesDAO;
import com.courses.barbie.dao.ShopDAO;
import com.courses.barbie.entities.AccountEntity;
import com.courses.barbie.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accounts")
    public Iterable<AccountEntity> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/account")
    public AccountEntity getAccountById(@RequestParam("accountId") int accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping(value = "/account")
    public void changeAccountBalance(@RequestBody AccountBalanceDiffDTO accountBalanceDiff) {
        accountService.changeAccountBalance(accountBalanceDiff);
    }

}
