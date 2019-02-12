package com.logmei.springMVC.controller;

import com.logmei.springMVC.annotation.MyController;
import com.logmei.springMVC.annotation.MyRequestMapping;
import com.logmei.springMVC.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 13:20 2019/2/11
 * @ Description：
 * @ Modified By：
 * @Version: 1.0.0
 */
@MyController
@MyRequestMapping("/test")
public class TestController {
    @MyRequestMapping("/doTest1")
    public void doTest1(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("param") String param,@MyRequestParam("param1") String param1){
        try {
            response.getWriter().write("doTest1 success ! param="+ param+";param1="+param1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/doTest2")
    public void doTest2(HttpServletResponse response,HttpServletRequest request){
        try {
            response.getWriter().write("doTest2 success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
