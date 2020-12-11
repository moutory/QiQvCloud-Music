package com.qiqv.music.service.impl;

import com.qiqv.music.service.TransactionTestB;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TransactionTestBImpl implements TransactionTestB {



    @Transactional
    @Override
    public void completeB() {
        try{
            System.out.println("下面抛异常");
            throw  new IllegalArgumentException(".....");
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
