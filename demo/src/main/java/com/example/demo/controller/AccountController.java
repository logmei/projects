package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.service.IAccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AccountController {

    @Resource
    private IAccountService accountService;

    @RequestMapping(value="/addAccount",method = RequestMethod.POST)
    public String addAccount(@RequestParam double momey,@RequestParam String name){
        Account account=new Account();
        account.setMoney(momey);
        account.setName(name);
        accountService.saveAccount(account);
        return "success";

    }

    @RequestMapping(value = "accountList")
    public List<Account> getAccountList(){
        return accountService.getAccountList();
    }
}
