package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.exception.AccountException;

import java.util.List;

public interface IAccountService {

    int addAccount(String name,double money);


    Account getAccountById(int id);

    int updateAccount(String name,double money,int id);

    int deleteAccount(int id);

    List<Account> getAccountList();

    void judgeRational(int id) throws AccountException;
}
