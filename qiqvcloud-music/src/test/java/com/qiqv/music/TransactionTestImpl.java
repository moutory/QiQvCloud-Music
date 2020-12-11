package com.qiqv.music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TransactionTestImpl implements TransactionTest {

    @Autowired
    private TransactionTestB transactionTestB;

    @Override
    public void completeA() {
        try{
        transactionTestB.completeB();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
