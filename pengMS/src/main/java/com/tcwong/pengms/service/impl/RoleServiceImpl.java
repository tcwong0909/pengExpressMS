package com.tcwong.pengms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dao.RoleMapper;
import com.tcwong.pengms.dto.RoleRequest;
import com.tcwong.pengms.model.Role;
import com.tcwong.pengms.model.example.RoleExample;
import com.tcwong.pengms.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

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

    /**
     * Description 获取角色列表
     *
     * @param request 角色请求
     * @return
     * @author tcwong
     * @date 2020/9/3
     */
    @Override
    public PageInfo<Role> listRoles(RoleRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return new PageInfo<>(roles);
    }
}
