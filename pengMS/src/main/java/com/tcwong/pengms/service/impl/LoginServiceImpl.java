package com.tcwong.pengms.service.impl;


import com.tcwong.pengms.dao.UserMapper;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.UserExample;
import com.tcwong.pengms.service.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class LoginServiceImpl implements ILoginService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> login(String account) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(account);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public int login() {
        return 0;
    }
}
