package com.qiqv.music;

import com.qiqv.music.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Objects;

public class MainTest {

    @Autowired
    private TransactionTest transactionTest;

    public static void main(String[] args) throws NoSuchAlgorithmException {
        new MainTest().booleanTest();
    }

    private static void method1() {
        ArrayList<Long> longs = Lists.newArrayList(1L, 2L, 3L);
        ArrayList<Long> longs2 = Lists.newArrayList(1L, 2L);

        for (Long aLong : longs) {
            for (Long aLong1 : longs2) {
                if (!aLong.equals(aLong1)) {
                    System.out.println(aLong1);
                }
            }
        }

        System.out.println(Objects.equals(new Long(3555), new Long(3555)));
    }

    public void getPassword(String password) throws NoSuchAlgorithmException {
        System.out.println( MD5Utils.getEnCryptionStrByMD5(password));
    }

    public void CALCULATE()  {
        int total = 100 - (3 - 1) * 10 - 1;
        System.out.println(total);
    }

    public void testTransaction(){
        transactionTest.completeA();
    }


    public void subTest(){
        String text1 = "+5+6/8888=";
        String text2 = text1.replaceAll("^\\D+", StringUtils.EMPTY).replaceAll("\\D+$", StringUtils.EMPTY);
        System.out.println("text1 = " + text1);
        System.out.println("text2 = " + text2);
        boolean Y = true;
        boolean N = false;
        System.out.println(Boolean.compare(Y,N));

    }

    public void booleanTest(){
        Object a =  null;

    }


}
