package com.tcwong.pengms.service;


import com.tcwong.pengms.model.Role;

import java.util.List;

public interface IRoleService {
    int addRole(Role role);

    int deleteById(Integer id);

    int editRole(Role role);

    List<Role> getAllRoles();
}
