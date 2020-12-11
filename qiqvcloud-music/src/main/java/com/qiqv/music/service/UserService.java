package com.qiqv.music.service;

import com.qiqv.music.pojo.Admin;

public interface UserService {

    Admin verifyUser(String username,String password);

}
