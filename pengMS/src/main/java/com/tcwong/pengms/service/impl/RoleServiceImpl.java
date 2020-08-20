package com.tcwong.pengms.service.impl;

import com.tcwong.pengms.dao.RoleMapper;
import com.tcwong.pengms.model.Role;
import com.tcwong.pengms.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int editRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleMapper.selectByExample(null);
    }
}
