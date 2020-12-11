package com.qiqv.music.service.impl;

import com.qiqv.music.service.TransactionTestA;
import com.qiqv.music.service.TransactionTestB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionTestAImpl implements TransactionTestA {

    @Autowired
    private TransactionTestB transactionTestB;

    @Transactional
    @Override
    public void completeA() {
        try{
        transactionTestB.completeB();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("成功跑完");
    }
}
