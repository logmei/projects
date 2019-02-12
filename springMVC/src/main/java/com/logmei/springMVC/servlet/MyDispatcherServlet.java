package com.logmei.springMVC.servlet;

import com.logmei.springMVC.utils.InitHandlerMapping;
import com.logmei.springMVC.utils.InitThemeResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 20:32 2019/2/5
 * @ Description：
 * @ Modified By：
 * @Version: 1.0.0
 */
public class MyDispatcherServlet extends HttpServlet {
    private Properties properties = new Properties();//父类hashtable<object,object>，存储资源的键值对
    private List<String> classNames = new ArrayList<>();
    private Map<String,Object> ioc = new HashMap<>();
    private Map<String,Object> controllerMapping = new HashMap<>();
    private Map<String, Method> methodMap = new HashMap<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化配置文件
        InitThemeResolver.doLoadConfig(properties,config.getInitParameter("contextConfigLocation"));
        //扫描包存入classNames中
        InitThemeResolver.doScanner(classNames,properties.getProperty("scanPackages"));
        //拿到扫描到的类，通过反射机制实例化到ioc中
        InitThemeResolver.doInstance(ioc,classNames);
        //初始化handlerMapping(将url对应到method)
        InitHandlerMapping.initHandlerMapping(controllerMapping,methodMap,ioc);
    }

    private void doDispatch(HttpServletRequest request,HttpServletResponse response) throws Exception{
        if(methodMap.isEmpty())return;
        String url = request.getRequestURI();
        System.out.println("uri:"+url);
        String contextPath = request.getContextPath();
        System.out.println("contextpath:"+contextPath);
        url = url.replace(contextPath,"").replaceAll("/+","/");

        if(!methodMap.containsKey(url)){
            response.getWriter().write("404 not found method");
            return;
        }

        Method method = methodMap.get(url);
        //获取方法的参数列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        //获取请求的参数
        Map<String,String[]> parameterMap = request.getParameterMap();
        //保存参数值
        Object[] parameterValues = new Object[parameterTypes.length];
        for (int i = 0; i<parameterTypes.length ; i++) {
            //参数名称
            String requestParam = parameterTypes[i].getSimpleName();
            if(requestParam.equals("HttpServletRequest")){
                parameterValues[i] = request;
                continue;
            }
            if(requestParam.equals("HttpServletResponse")){
                parameterValues[i] = response;
                continue;
            }
            if(requestParam.equals("String")){
                for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
                    if(parameterTypes[i].getName().equals(entry.getKey())) {
                        String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                        parameterValues[i] = value;
                        break;
                    }
                }


            }

        }
        Arrays.stream(parameterValues).forEach(System.out::println);

    }


}
