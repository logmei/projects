package com.logmei.policyfixedifelse.annotation;

import java.lang.annotation.*;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 22:27 2019/2/2
 * @ Description：handler自定义注解
 * @ Modified By：
 * @Version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    String value();
}
