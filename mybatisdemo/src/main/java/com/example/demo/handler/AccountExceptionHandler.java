package com.example.demo.handler;

import com.example.demo.domain.ResultObject;
import com.example.demo.exception.AccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public  class AccountExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccountExceptionHandler.class);
    @ExceptionHandler(value=AccountException.class)
    @ResponseBody
    public ResultObject accountHandler(AccountException e){
        logger.error(e.getMessage());
       return  new ResultObject(e.getCode(),e.getMessage(),null)   ;
    }
}
