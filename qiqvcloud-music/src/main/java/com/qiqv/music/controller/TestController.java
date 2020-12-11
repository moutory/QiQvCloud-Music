package com.qiqv.music.controller;

import com.qiqv.music.service.TransactionTestA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TransactionTestA transactionTest;

    @RequestMapping("/transaction")
    public void testTransaction(){
        transactionTest.completeA();
    }

}
