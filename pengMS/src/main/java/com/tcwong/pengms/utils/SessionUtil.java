package com.tcwong.pengms.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * Description
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public class SessionUtil {


    private static HttpSession session = getSession();

    private static HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = ((ServletRequestAttributes) requestAttributes).getRequest().getSession();
        //设置session 过期时间2小时
        session.setMaxInactiveInterval(2*60*60);
        return session;
    }

    public static void setAttribute(String sessionKey, Object sessionValue) {
        String attributeString = JSONObject.toJSONString(sessionValue);
        session.setAttribute(sessionKey,attributeString);
    }

    public static <V> V getAttribute(String sessionKey,Class<V> clazz) {
        String attribute = (String)session.getAttribute(sessionKey);
        return JSONObject.parseObject(attribute,clazz);
    }

}
