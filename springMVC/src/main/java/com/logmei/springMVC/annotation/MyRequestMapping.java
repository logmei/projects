package com.logmei.springMVC.annotation;

import java.lang.annotation.*;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 20:26 2019/2/5
 * @ Description：
 * @ Modified By：
 * @Version: 1.0.0
 */
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyRequestMapping {
    String value();
}
