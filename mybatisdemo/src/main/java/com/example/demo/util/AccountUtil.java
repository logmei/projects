package com.example.demo.util;

import com.example.demo.domain.ResultObject;

public class AccountUtil {

    public static   ResultObject success(Object object){
        return new ResultObject(1,"成功",object);
    }

    public static ResultObject sucess(){
        return new ResultObject(1,"成功",null);
    }

    public static ResultObject error(int code,String msg){
        return new ResultObject(0,msg,null);
    }
}
