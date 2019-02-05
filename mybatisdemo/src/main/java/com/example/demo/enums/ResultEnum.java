package com.example.demo.enums;

public enum ResultEnum {
    RATIONAL_MONEY(1,"合适"),
    WRONG_MONEY(0,"错误钱数");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
