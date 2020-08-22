package com.tcwong.pengms.service.impl;


import com.tcwong.pengms.dao.UserMapper;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.model.LoginLogExample;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.UserExample;
import com.tcwong.pengms.service.ILoginService;
import com.tcwong.pengms.service.LoginLogService;
import com.tcwong.pengms.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private LoginLogService loginLogService;
    @Override
    public List<User> doLogin(String account) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(account);
        List<User> users = userMapper.selectByExample(userExample);
        String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        LoginLog loginLog = new LoginLog();
        loginLog.setLoginIp(ipAddr);
        loginLog.setUsername("admin");
        loginLog.setLoginTime(new Date());
        loginLogService.insert(loginLog);
        return users;
    }

    @Override
    public int login() {
        return 0;
    }
}
