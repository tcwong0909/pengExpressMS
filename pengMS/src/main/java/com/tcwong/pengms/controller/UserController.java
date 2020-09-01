package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebPageResponse;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 */
@RestController
@RequestMapping("/pengms/user/")
public class UserController {

    @Resource
    private UserService userService;

    @LogFilter(description = "用户添加",logOperationType = LogOperationType.ADD)
    @PostMapping("/add")
    public WebResponse addUser(@RequestBody User user) {
        int num = userService.addUser(user);
        if (num > 0) {
            return WebResponse.success("添加成功");
        }
        return WebResponse.failed("添加失败");
    }

    @LogFilter(description = "用户删除",logOperationType = LogOperationType.DELETE)
    @DeleteMapping("/delete/{ids}")
    public WebResponse deleteByIds(@PathVariable String ids) {
        int num = userService.deleteByIds(ids);
        if (num > 0) {
            return WebResponse.success("删除成功");
        }
        return WebResponse.failed("删除失败");
    }

    @LogFilter(description = "用户修改",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/edit")
    public WebResponse editUser(@RequestBody User user) {
        int num = userService.editUser(user);
        if (num > 0) {
            return WebResponse.success("修改成功");
        }
        return WebResponse.failed("删除失败");
    }

    @PostMapping("/getAllByPage")
    public WebResponse getAllByPage(Integer page, Integer size, String username,String account,
                                    Integer sex, Integer fkRoleid) {
        WebPageResponse pageResponse = userService.getAllByPage(page, size, username, account, sex, fkRoleid);
        if (pageResponse != null) {
            return WebResponse.success(pageResponse);
        }
        return WebResponse.failed("查询失败");
    }

    @LogFilter(description = "修改密码",logOperationType = LogOperationType.UPDATE)
    @PutMapping("/password")
    public WebResponse editPassword(@RequestBody User user) {
        int num = userService.editPassword(user);
        if (num == 0) {
           return WebResponse.failed("密码不对，请重新输入");
        } else if (num > 0) {
           return WebResponse.success("密码修改成功，请重新登录");
        }
        return WebResponse.failed("修改失败，请重新输入");
    }

}
