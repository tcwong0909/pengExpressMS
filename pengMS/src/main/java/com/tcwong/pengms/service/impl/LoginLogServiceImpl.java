package com.tcwong.pengms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dao.LoginLogMapper;
import com.tcwong.pengms.dto.LoginLogRequest;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.model.example.LoginLogExample;
import com.tcwong.pengms.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public Integer insert(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }

    @Override
    public PageInfo<LoginLog> listLoginLog(LoginLogRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        LoginLogExample loginLogExample = new LoginLogExample();
        List<LoginLog> loginLogs = loginLogMapper.selectByExample(loginLogExample);
        return new PageInfo<>(loginLogs);
    }
}
