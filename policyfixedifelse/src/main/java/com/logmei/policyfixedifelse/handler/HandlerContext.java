package com.logmei.policyfixedifelse.handler;

import com.logmei.policyfixedifelse.tools.ApplicationContextProvider;

import java.util.Map;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:24 2019/2/2
 * @ Description：处理器上下文
 * @ Modified By：
 * @Version: 1.0.0
 */

@SuppressWarnings("unchecked")
public class HandlerContext {


    private Map<String,Class> handlerMap;

    public HandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public AbstractHandler getHandler(String type){
        Class clazz = handlerMap.get(type);
        if(clazz == null){
            throw new IllegalArgumentException(" not found handler for type :"+type);
        }
        return (AbstractHandler) ApplicationContextProvider.getBean(clazz);
    }
}
