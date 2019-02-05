package com.example.demo.controller;

import com.example.demo.domain.ResultObject;
import com.example.demo.entity.Account;
import com.example.demo.exception.AccountException;
import com.example.demo.service.IAccountService;
import com.example.demo.util.AccountUtil;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account" )
public class AccountController {

    @Resource
    IAccountService accountService;


    @RequestMapping(value = "/postAccount",method = RequestMethod.POST)
    public ResultObject postAccount(@Valid Account account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return AccountUtil.error(0,bindingResult.getFieldError().getDefaultMessage());
        }

        int ret=accountService.addAccount(account.getName(),account.getMoney());
        if(ret==1){
            return AccountUtil.sucess();
        }
        else return AccountUtil.error(-1,"保存失败");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id") int id){
        return accountService.getAccountById(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public String deleteAccount(@PathVariable("id") int id){
        if(accountService.deleteAccount(id)==1)return "success";
        else return "error";
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public int updateAccount(@PathVariable("id") int id,@RequestParam String name,@RequestParam double money){
        return accountService.updateAccount(name,money,id);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Account> getAccountList(){

        return accountService.getAccountList();
    }

    @RequestMapping(value = "/judgeRational/{id}",method = RequestMethod.GET)
    public void judgeRational(@PathVariable("id") int id) throws AccountException {

            accountService.judgeRational(id);
    }
}
