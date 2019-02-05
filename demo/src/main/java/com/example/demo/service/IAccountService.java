package com.example.demo.service;

import com.example.demo.entity.Account;

import java.util.List;

public interface IAccountService {
    void saveAccount(Account account);

    List<Account> getAccountList();
}
