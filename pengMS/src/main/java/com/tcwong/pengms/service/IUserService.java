package com.tcwong.pengms.service;


import com.tcwong.pengms.model.User;
import com.tcwong.pengms.utils.WebPageResponse;

public interface IUserService {
    int addUser(User user);

    int deleteByIds(String ids);

    int editUser(User user);

    WebPageResponse getAllByPage(Integer page, Integer size, String username, String account, Integer sex, Integer fkRoleid);

    int editPassword(User user);
}
