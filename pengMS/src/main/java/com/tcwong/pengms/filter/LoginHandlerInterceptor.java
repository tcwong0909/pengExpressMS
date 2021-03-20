package com.tcwong.pengms.filter;

import com.alibaba.fastjson.JSONObject;
import com.tcwong.pengms.base.WebResponse;
import com.tcwong.pengms.constant.LoginResultEnum;
import com.tcwong.pengms.constant.SessionConstant;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.utils.PengContext;
import com.tcwong.pengms.utils.RateLimitUtil;
import com.tcwong.pengms.utils.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description 自定义拦截器
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired private RateLimitUtil rateLimitUtil;

    /**
     * Description 前置拦截
     *
     * @param
     * @return
     * @author tcwong
     * @date 2020/8/22
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 限流处理
        if (rateLimitUtil.rateLimit(request, handler)) {
            response.setStatus(Integer.parseInt(LoginResultEnum.REQUEST_FREQUENT.getResultCode()));
            response.setContentType("application/json;charset=UTF-8");
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setMessage(LoginResultEnum.REQUEST_FREQUENT.getResultMessage());
            response.getWriter().write(JSONObject.toJSONString(webResponse));
            return false;
        }
        User user = SessionUtil.getAttribute(SessionConstant.SESSION_USER, User.class);
        if (user == null) {
            // session 失效时 重新登陆
            response.setStatus(Integer.parseInt(LoginResultEnum.GONE_EXPIRE.getResultCode()));
            response.setContentType("application/json;charset=UTF-8");
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setMessage(LoginResultEnum.GONE_EXPIRE.getResultMessage());
            response.getWriter().write(JSONObject.toJSONString(webResponse));
            return false;
        }
        PengContext.setUser(user);
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView)
            throws Exception {}

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}
}
