package com.qiqv.music.service.impl;

import com.qiqv.music.dao.AdminMapper;
import com.qiqv.music.pojo.Admin;
import com.qiqv.music.pojo.AdminExample;
import com.qiqv.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin verifyUser(String username, String password) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if(!admins.isEmpty()){
            return admins.get(0);
        }
        return null;
    }
}
