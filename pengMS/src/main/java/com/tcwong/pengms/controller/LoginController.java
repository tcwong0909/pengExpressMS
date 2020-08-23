package com.tcwong.pengms.controller;

import com.tcwong.pengms.dto.LoginRequest;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.LoginService;
import com.tcwong.pengms.utils.Log;
import com.tcwong.pengms.utils.LogdicType;
import com.tcwong.pengms.utils.WebResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
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

    @Log(behavior = "登录", fkTypeid = LogdicType.LOGIN)
    @PostMapping("/doLogin")
    public WebResponse doLogin(@RequestBody LoginRequest request) {
        User user = loginService.doLogin(request);
        user.setPassword(null);
        return WebResponse.success(user);
    }

    @Log(behavior = "注销登录", fkTypeid = LogdicType.LOGOUT)
    @PostMapping("/doLogout")
    public WebResponse logout() {
        Session session = SecurityUtils.getSubject().getSession();
        session.stop();
        return WebResponse.success("注销成功");
    }
}
