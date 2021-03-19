package com.tcwong.pengms.service;

import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dto.LoginLogRequest;
import com.tcwong.pengms.model.LoginLog;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
public interface LoginLogService {
    Integer insert(LoginLog loginLog);

    PageInfo<LoginLog> listLoginLog(LoginLogRequest request);
}
