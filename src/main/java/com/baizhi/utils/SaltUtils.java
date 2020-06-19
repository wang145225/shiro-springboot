package com.baizhi.utils;

import java.util.Random;

/**
 * 随机盐工具类
 *
 */
public class SaltUtils {

    /**
     * 生成随机盐
     * @return
     */
    public static String getSalt(int n){
        //生成一个char数组
        char[] salts="DJBKHFJHSFJHHJFFHJDHJFDSHJHJHJHJSAJHDHJASDVGHsghdsahgdghsadghagh455455".toCharArray();
        //创建一个可变的字符串
        StringBuilder sb=new StringBuilder();
        //循环生成随机盐
        for (int i=0;i<n;i++){
            //利用随机数来取值
            char c = salts[new Random().nextInt(salts.length)];
            sb.append(c);
        }

        return sb.toString();
    }
}
