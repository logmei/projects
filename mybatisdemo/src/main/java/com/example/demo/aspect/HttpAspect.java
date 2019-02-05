package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger= LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("@annotation(com.example.demo.annotation.OnlyAdmin)")
    public void onlyAdmin(){}

    @Pointcut("within(com.example.demo.service.*)")
    public void onlyAdminClass(){}

    @Pointcut("execution(* com.example.demo.service..get*(*))&&within(com.example.demo..*)")
    public void onlyAdminExecution(){}

    @Before("onlyAdminExecution()")
    public void beforeAnno(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}",request.getRequestURL());
        logger.info("ip={}",request.getRemoteHost());
        logger.info("remoteUser={}",request.getRemoteUser());
        logger.info("method={}",request.getMethod());
        logger.info("class={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("args={}",joinPoint.getArgs());
    }

    @AfterReturning(returning = "object",pointcut = "onlyAdminExecution()")
    public void getResult(Object object){
        logger.info("returnning={}",object);

    }

    @After("onlyAdminExecution()")
    public void doAfter(){
        logger.info("####after");
    }
}
