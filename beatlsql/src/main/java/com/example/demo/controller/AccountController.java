package com.example.demo.controller;

import com.example.demo.dao.IAccountDao;
import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired(required = false)
    IAccountDao iAccountDao;

    @RequestMapping(value = "/getAccountByName",method = RequestMethod.GET)
    public Account getAccountByName(){
        return iAccountDao.selectAccountByName("logmei");
    }
}
