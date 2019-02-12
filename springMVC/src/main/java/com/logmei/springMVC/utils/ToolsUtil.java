package com.logmei.springMVC.utils;

/**
 * @ Author     ：logmei.
 * @ Date       ：Created in 9:57 2019/2/11
 * @ Description：工具类
 * @ Modified By：
 * @Version: 1.0.0
 */
public class ToolsUtil {

    //首字母小写
    public static String toLowerFirstWord(String name){
        char[] charArray = name.toCharArray();
        charArray[0]+=32;
        return String.valueOf(charArray);
    }
}
