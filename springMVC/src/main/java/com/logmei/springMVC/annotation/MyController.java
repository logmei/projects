package com.logmei.springMVC.annotation;

import java.lang.annotation.*;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 19:33 2019/2/5
 * @ Description：
 * @ Modified By：
 * @Version: 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyController {
}
