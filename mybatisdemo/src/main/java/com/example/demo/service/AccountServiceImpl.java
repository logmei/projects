package com.example.demo.service;

import com.example.demo.dao.AccountMapper;
import com.example.demo.entity.Account;
import com.example.demo.enums.ResultEnum;
import com.example.demo.exception.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService{

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Resource
    private AccountMapper accountMapper;


    public int addAccount(String name,double money){
        return accountMapper.add(name,money);
    }

    @Override
    public Account getAccountById(int id) {
        logger.info("getAccountById()");
        return accountMapper.getAccount(id);
    }

    @Override
    public int updateAccount(String name, double money, int id) {
        return accountMapper.update(name,money,id);
    }

    @Override
    public int deleteAccount(int id) {
        return accountMapper.delete(id);
    }

    @Override
    public List<Account> getAccountList() {
        logger.info("getAccountList()");
        return accountMapper.findAccountList();
    }

    public void judgeRational(int id) throws AccountException{
       Account account = accountMapper.getAccount(id);
       if(account.getMoney()<10){
           throw new AccountException(ResultEnum.WRONG_MONEY);
       }else if(account.getMoney() > 10 && account.getMoney() < 20){
           throw new AccountException(ResultEnum.RATIONAL_MONEY);
       }
    }
}
