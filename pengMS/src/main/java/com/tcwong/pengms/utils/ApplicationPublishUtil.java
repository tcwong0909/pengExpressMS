package com.tcwong.pengms.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * Description 时间发布工具类
 *
 * @author tcwong
 * @date 2020/8/24 Since 1.8
 */
public class ApplicationPublishUtil {

    private static ApplicationContext applicationContext;

    /**
     * Description 发布时间
     *
     * @param event 事件
     * @return
     * @author tcwong
     * @date 2020/8/24
     */
    public static void publish(ApplicationEvent event) {
        if (applicationContext == null) {
            applicationContext = ApplicationBeanUtil.getApplicationContext();
        }
        applicationContext.publishEvent(event);
    }
}
