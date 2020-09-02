package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.base.BaseException;
import com.tcwong.pengms.constant.SessionConstant;
import com.tcwong.pengms.dao.UserMapper;
import com.tcwong.pengms.dto.LoginRequest;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.model.UserExample;
import com.tcwong.pengms.service.LoginLogService;
import com.tcwong.pengms.service.LoginService;
import com.tcwong.pengms.utils.IpUtil;
import com.tcwong.pengms.utils.RateLimitUtil;
import com.tcwong.pengms.utils.SessionUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private RateLimitUtil rateLimitUtil;

    @Override
    public User doLogin(LoginRequest request) {

        if (rateLimitUtil.rateLimit(httpServletRequest, new HandlerMethod(this, this.getClass().getMethods()[0]))) {
            throw new BaseException("0115");
        }
        User user;
        String account = request.getAccount();
        String password = request.getPassword();
        UserExample accountExample = new UserExample();
        accountExample.createCriteria().andUserAccountEqualTo(account);
        List<User> accountUserList = userMapper.selectByExample(accountExample);
        if (CollectionUtils.isEmpty(accountUserList)) {
            throw  new BaseException("0112");
        }
        UserExample accountPasswordExample = new UserExample();
        accountPasswordExample.createCriteria().andUserAccountEqualTo(account)
                .andPasswordEqualTo(password);
        List<User> accountPasswordUserList = userMapper.selectByExample(accountPasswordExample);
        if (CollectionUtils.isNotEmpty(accountPasswordUserList)) {
            user = accountPasswordUserList.get(0);
            user.setPassword(null);
            String ipAddr = IpUtil.getIpAddr(httpServletRequest);
            LoginLog loginLog = new LoginLog();
            loginLog.setLoginIp(ipAddr);
            loginLog.setUsername(user.getUsername());
            loginLog.setAccount(account);
            loginLog.setLoginTime(new Date());
            loginLogService.insert(loginLog);
            SessionUtil.setAttribute(SessionConstant.SESSION_USER,user);
        } else {
            throw new BaseException("0113");
        }
        return user;
    }

    @Override
    public int login() {
        return 0;
    }
}
