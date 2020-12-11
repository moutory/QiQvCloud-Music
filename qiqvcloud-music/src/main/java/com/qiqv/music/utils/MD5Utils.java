package com.qiqv.music.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  密码加密工具类，使用MD5算法对敏感信息进行加密
 */
public class MD5Utils {
    public static String getEnCryptionStrByMD5(String word) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String enCryptionWord = Base64.encodeBase64String(md5.digest(word.getBytes()));
        return enCryptionWord;
    }
    public static void main(String[] args) {
        String str = "aabbcc";
        String newStr = str.replace("bb","dd");
        System.out.println(newStr);
    }
}
