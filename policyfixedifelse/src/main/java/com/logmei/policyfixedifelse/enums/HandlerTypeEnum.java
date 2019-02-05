package com.logmei.policyfixedifelse.enums;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 10:39 2019/2/3
 * @ Description：订单类型
 * @ Modified By：
 * @Version: 1.0.0
 */
public enum HandlerTypeEnum {
    NORMAL("1","普通订单"),
    GROUP("2","团购订单"),
    PROMOTION("3","促销订单");
    private  String code;
    private String description;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    HandlerTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }}
