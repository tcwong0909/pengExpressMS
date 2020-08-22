package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.LoginLogMapper;
import com.tcwong.pengms.model.LoginLog;
import com.tcwong.pengms.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
