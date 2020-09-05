package com.tcwong.pengms.service;


import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dto.RoleRequest;
import com.tcwong.pengms.model.Role;

import java.util.List;

public interface RoleService {
    int addRole(Role role);

    int deleteById(Integer id);

    int editRole(Role role);

    List<Role> getAllRoles();

    /**
     * Description 获取角色列表
     *
     * @param request 角色请求
     * @return
     * @author tcwong
     * @date 2020/9/3
     */
    PageInfo<Role> listRoles(RoleRequest request);
}
