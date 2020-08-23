package com.tcwong.pengms.filter;

import com.alibaba.fastjson.JSONObject;
import com.tcwong.pengms.constant.LoginResultEnum;
import com.tcwong.pengms.constant.SessionConstant;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.utils.LogUtil;
import com.tcwong.pengms.utils.SessionUtil;
import com.tcwong.pengms.utils.WebResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description 自定义拦截器
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * Description 前置拦截
     *
     * @param
     * @return
     * @author tcwong
     * @date 2020/8/22
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = SessionUtil.getAttribute(SessionConstant.SESSION_USER, User.class);
        if (user == null) {
            //session 失效时 重新登陆
            response.setStatus(Integer.parseInt(LoginResultEnum.GONE_EXPIRE.getResultCode()));
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            WebResponse<Object> webResponse = new WebResponse<>();
            webResponse.setMessage(LoginResultEnum.GONE_EXPIRE.getResultMessage());
            response.getWriter().write(JSONObject.toJSONString(webResponse));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
