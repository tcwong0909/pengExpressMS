package com.tcwong.pengms.service;


import com.tcwong.pengms.dto.LoginRequest;
import com.tcwong.pengms.model.User;

public interface LoginService {

    /**
     * 登录
     * @param request
     * @return
     */
    User doLogin(LoginRequest request);

    /**
     * 注销登录
     * @return
     */
    int login();
}
