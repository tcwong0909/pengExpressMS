package com.tcwong.pengms.service;


import com.tcwong.pengms.model.User;

import java.util.List;

public interface ILoginService {

    /**
     * 登录
     * @param account
     * @return
     */
    List<User> login(String account);

    /**
     * 注销登录
     * @return
     */
    int login();
}