package com.tcwong.pengms.service;


import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.dto.UserRequest;
import com.tcwong.pengms.dto.UserVO;
import com.tcwong.pengms.model.User;

public interface UserService {
    int addUser(User user);

    int deleteByIds(String ids);

    int editUser(User user);

    int editPassword(User user);

    /**
     * Description 查询用户列表
     *
     * @param request 用户管理请求
     * @return
     * @author tcwong
     * @date 2020/9/2
     */
    PageInfo<UserVO> listUsers(UserRequest request);
}
