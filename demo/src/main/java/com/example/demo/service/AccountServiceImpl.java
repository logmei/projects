package com.example.demo.service;

import com.example.demo.dao.IAccountDao;
import com.example.demo.entity.Account;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Resource
    public IAccountDao accountDao;

    public void saveAccount(Account account){
         accountDao.save(account);
    }

    public List<Account> getAccountList(){
        return accountDao.findAll();
    }
}
