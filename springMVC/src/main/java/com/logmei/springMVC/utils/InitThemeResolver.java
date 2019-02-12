package com.logmei.springMVC.utils;

import com.logmei.springMVC.annotation.MyController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 15:31 2019/2/9
 * @ Description：资源处理器
 * @ Modified By：
 * @Version: 1.0.0
 */
public class InitThemeResolver {

    /**
     * 读取配置文件内容到properties
     *
     * Properties继承自Hashtable：
     * getProperty(String key):通过指定的键在属性列表中搜索属性，（通过key获取value）
     * load(InputStream inStream):从输入流中获取属性列表 两种用法如：
     *     Class中的InputStream inStream = getClass().getResourceAsStream("资源名称");
     *     InputStream inStream = new BufferInputStream(new FileInputStream(filepath));
     * store(OutputStream out,String comments) 举例如：
     *     Properties properties = new Properties();
     *     properties.load(new FileInputStream(filepath));
     *     OutputStream out = new FileOutputStream(filepath);
     *     properties.setProperty(key,value);
     *     properties.store(out,"注释")
     *clear()：清除所有的key-value
     *
     *
     * @param location
     */
    public static void doLoadConfig(Properties properties,String location){

        InputStream resouceAsStream = InitThemeResolver.class.getClassLoader().getResourceAsStream(location);
        try {
            properties.load(resouceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != resouceAsStream){
                try {
                    resouceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * 扫描包下的所有文件到classNames
     * @param classNames
     * @param packageName
     */
    public static void doScanner(List<String> classNames, String packageName){

        //把所有.换成/
       String packageName1 = packageName.replace(".","/");
//        System.out.println("classLoader resource:"+InitThemeResolver.class.getClassLoader().getResource(packageName));
//        System.out.println("classLoader resource:"+InitThemeResolver.class.getClassLoader().getResource(packageName+"/controller/TestController.class"));
        URL url = InitThemeResolver.class.getClassLoader().getResource(packageName1);
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()){
            if(file.isDirectory()){
                //递归读取包
                doScanner(classNames,packageName+"."+file.getName());

            }else{
                String className = packageName+"."+file.getName().replace(".class","");
                classNames.add(className);

            }
        }
    }

    /**
     * 实例化对象
     * @param classNames
     */
    public static void doInstance(Map<String,Object> ioc, List<String> classNames){
        if(classNames.isEmpty())return;
        for (String className : classNames){
            try {
                //根据文件名，反射来实例化对象(实例化MyController注解的类,并添加到ioc中)
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(MyController.class)){
                    ioc.put(ToolsUtil.toLowerFirstWord(className),clazz.newInstance());
                }else {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        InitThemeResolver initThemeResolver = new InitThemeResolver();
        URL url = initThemeResolver.getClass().getClassLoader().getResource("");
        System.out.println(url);
    }
}
