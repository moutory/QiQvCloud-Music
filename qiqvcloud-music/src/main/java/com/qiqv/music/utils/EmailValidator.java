package com.qiqv.music.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator extends RegularValidator {

    /**
     * 邮箱规则分解
     *  \w+([-+.]\w+)* 表示任何一个数字、大小写字符或者-/+/.
     *  \w+([-.]\w+)*  和上面一样，但是不包括+
     *  合起来就是前后需要匹配：任意字符 + @ + 任意字符 + . + 任意字符
     */
    private static final String EMAIL_RULE="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public boolean isLegal(String input){
        return super.isMatch(EMAIL_RULE,input);
    }

}
