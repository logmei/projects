package com.logmei.springMVC.utils;

import com.logmei.springMVC.annotation.MyController;
import com.logmei.springMVC.annotation.MyRequestMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 10:03 2019/2/11
 * @ Description：初始化handlerMapping
 * @ Modified By：
 * @Version: 1.0.0
 */
public class InitHandlerMapping {
    public static void initHandlerMapping(Map<String,Object> controllerMap, Map<String, Method> methodMap,Map<String,Object> ioc){
        if(ioc.size()==0)return;
        for (Map.Entry<String,Object> object : ioc.entrySet()){
            Class<? extends Object> clazz = object.getValue().getClass();
            //判断是否为MyController注解的类
            if(!clazz.isAnnotationPresent(MyController.class))continue;
            //拼接url
            StringBuffer baseUrl = new StringBuffer();
            if(clazz.isAnnotationPresent(MyRequestMapping.class)){
                MyRequestMapping annotation = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl.append(annotation.value());
            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods){
                if(!method.isAnnotationPresent(MyRequestMapping.class))continue;
                String url = baseUrl.toString()+"/"+method.getAnnotation(MyRequestMapping.class).value();
                url = url.replaceAll("/+","/");
                methodMap.put(url,method);
                controllerMap.put(url,clazz);
                System.out.println(url+","+method);
            }
        }

    }

}
