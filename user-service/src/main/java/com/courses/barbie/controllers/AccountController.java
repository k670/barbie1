package com.courses.barbie.controllers;

import com.courses.barbie.AccountBalanceDiffDTO;
import com.courses.barbie.entities.AccountEntity;
import com.courses.barbie.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AccountController {


    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping(value = "/accounts")
    public Iterable<AccountEntity> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/account")
    public AccountEntity getAccountById(@RequestParam("accountId") int accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping(value = "/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void changeAccountBalance(@RequestBody AccountBalanceDiffDTO accountBalanceDiff) {
        log.info("AccountBalanceDiffDTO {}, {}",accountBalanceDiff.getAccountId(),accountBalanceDiff.getBalanceDifference());
        accountService.changeAccountBalance(accountBalanceDiff);
    }

    @PostMapping(value = "/account/clothes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addClothes(@RequestBody ClothesDTO clothesDTO) {
        accountService.addClothes(clothesDTO);
    }

}
