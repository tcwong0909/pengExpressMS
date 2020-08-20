package com.tcwong.pengms.service.impl;


import com.tcwong.pengms.dao.UserMapper;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.UserExample;
import com.tcwong.pengms.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> login(String account) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(account);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public int login() {
        return 0;
    }
}
