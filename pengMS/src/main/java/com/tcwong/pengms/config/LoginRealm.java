package com.tcwong.pengms.config;


import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginRealm extends AuthorizingRealm {
    @Resource
    private LoginService loginService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        Session session = SecurityUtils.getSubject().getSession();
        User user  = loginService.doLogin(null);
        session.setAttribute("user",user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), ByteSource.Util.bytes(user.getAccount()), getName());
        return info;
    }
}
