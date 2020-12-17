package com.qiqv.music.controller;

import com.qiqv.music.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个controller用于提供常用的静态变量给其他controller调用
 */
@RestController
public class BasicController {

    @Autowired
    public RedisOperator redisOperator;

    public static final String USER_TOKEN_SESSION = "user-redis-token";
}
