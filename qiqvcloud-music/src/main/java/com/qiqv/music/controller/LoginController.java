package com.qiqv.music.controller;

import com.qiqv.music.pojo.Admin;
import com.qiqv.music.service.UserService;
import com.qiqv.music.utils.MD5Utils;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/verifyUser")
    public QiqvJSONResult login(String username, String password) throws NoSuchAlgorithmException {
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return QiqvJSONResult.errorMsg("用户名和密码不允许为空");
        }
        Admin admin = userService.verifyUser(username, MD5Utils.getEnCryptionStrByMD5(password));
        if(admin!=null){
            //TODO 将用户存到Redis中
            return QiqvJSONResult.ok(admin);
        }
        return QiqvJSONResult.errorMsg("用户名或者密码错误，请重新输入...");
    }

}
