package com.tcwong.pengms.controller;

import com.tcwong.pengms.base.LogFilter;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LogOperationType;
import com.tcwong.pengms.dto.LoginRequest;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 */
@RestController
@RequestMapping("/pengms/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @LogFilter(description = "用户登录", logOperationType = LogOperationType.LOGIN)
    @PostMapping("/doLogin")
    public WebResponse doLogin(@RequestBody LoginRequest request) {
        User user = loginService.doLogin(request);
        user.setPassword(null);
        return WebResponse.success(user);
    }

    @PostMapping("/doLogout")
    public WebResponse logout() {
        return WebResponse.success("注销成功");
    }
}
