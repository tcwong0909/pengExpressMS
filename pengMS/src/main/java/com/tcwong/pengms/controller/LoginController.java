package com.tcwong.pengms.controller;

import com.tcwong.pengms.dto.LoginBean;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.ILoginService;
import com.tcwong.pengms.utils.Log;
import com.tcwong.pengms.utils.LogdicType;
import com.tcwong.pengms.utils.WebResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录
 */
@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    @Log(behavior = "登录",fkTypeid = LogdicType.LOGIN)
    @PostMapping("/login")
    public WebResponse login(@RequestBody LoginBean user){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user.getAccount(), user.getPassword()));
            User user1 = loginService.login(user.getAccount()).get(0);
            user1.setPassword(null);
            return WebResponse.success(user1);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return WebResponse.failed("登录失败");
    }

    @Log(behavior = "注销登录",fkTypeid = LogdicType.LOGOUT)
    @PostMapping("/logout")
    public WebResponse logout(){
        Session session = SecurityUtils.getSubject().getSession();
        session.stop();
        return WebResponse.success("注销成功");
    }
}
