package com.qiqv.music.utils;

import java.util.regex.Pattern;

/**
 * 定义正则校验类
 */
public class RegularValidator {

    /**
     * 判断文本是否能够匹配正则表达式的要求
     * @param pattern
     * @param targetStr
     * @return
     */
    public boolean isMatch(String pattern,String targetStr){
        return Pattern.matches(pattern,targetStr);
    }

    /**
     * 判断文本中是否存在可以匹配正则表达式的内容（也就是部分匹配）
     * @return
     */
    public boolean isFind(String pattern,String input){
        return Pattern.compile(pattern).matcher(input).find();
    }

}
