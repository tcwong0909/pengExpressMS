package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.Role;
import com.tcwong.pengms.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色
 */
@RestController
@RequestMapping("/pengms/role/")
public class RoleController {

    @Resource
    private RoleService roleService;

    @LogFilter(description = "角色添加",logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addRole(@RequestBody Role role) {
        int num = roleService.addRole(role);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "角色删除",logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{id}")
    public WebResponse deleteById(@PathVariable Integer id) {
        int num = roleService.deleteById(id);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @LogFilter(description = "角色修改",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/edit")
    public WebResponse editRole(@RequestBody Role role) {
        int num = roleService.editRole(role);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("修改失败");
    }

    @GetMapping("/getAll")
    public WebResponse getAllRoles(){
        List<Role> allRoles = roleService.getAllRoles();
        if (allRoles != null) {
            return WebResponse.success(allRoles);
        }
        return WebResponse.failed("查询失败");
    }
}
