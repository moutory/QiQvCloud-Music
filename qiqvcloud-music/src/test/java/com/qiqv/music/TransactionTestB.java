package com.qiqv.music;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
@Transactional
public class TransactionTestB {

    public void completeB(){
        System.out.println("下面抛异常");
        throw new IllegalArgumentException("抛异常");
    }


}
