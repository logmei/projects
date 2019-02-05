package com.logmei.policyfixedifelse.handler.processor;

import com.google.common.collect.Maps;
import com.logmei.policyfixedifelse.annotation.HandlerType;
import com.logmei.policyfixedifelse.handler.HandlerContext;
import com.logmei.policyfixedifelse.scanner.ClassScaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:36 2019/2/2
 * @ Description：如何将处理器注册到spring中？
 *  1、扫描指定包中标有handlertype的类
 *  2、将注解中的类型值作为key,对应的类作为value保存在map中
 *  3、以上面的map作为构造函数的参数，初始化HandlerContext,将其注册到spring容器中
 * @ Modified By：
 * @Version: 1.0.0
 */
@Component
@SuppressWarnings("unchecked")
public class HandlerProcessor implements BeanFactoryPostProcessor {
    private static final String HANDLER_PACKAGE = "com.logmei.policyfixedifelse.handler";
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String,Class> map = Maps.newHashMapWithExpectedSize(3);
        ClassScaner.scaner(HANDLER_PACKAGE, HandlerType.class).forEach(clazz->{
            //获取注解中的类型值
            String type = clazz.getAnnotation(HandlerType.class).value();
            //注解中的类型作为key,clazz作为value存储在map中
            map.put(type,clazz);
        });
        //初始化handlerContext
        HandlerContext handlerContext = new HandlerContext(map);
        System.out.println("HandlerContext.class.getName():"+HandlerContext.class.getName());
        //注册到spring容器中
        beanFactory.registerSingleton(HandlerContext.class.getName(),handlerContext);

    }
}
