package com.tcwong.pengms.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description 请求过滤器
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
@Configuration
public class LoginFilter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginHandlerInterceptor());
        registration.addPathPatterns("/pengms/**");
        registration.excludePathPatterns(
                "/pengms/login/doLogin"
                ,"/pengms/login/doLogout");
    }
}
