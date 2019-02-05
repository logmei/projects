package com.example.demo.exception;

import com.example.demo.enums.ResultEnum;

public class AccountException extends RuntimeException{

    private int code;


    public AccountException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();

    }

    public int getCode() {
        return code;
    }



}
