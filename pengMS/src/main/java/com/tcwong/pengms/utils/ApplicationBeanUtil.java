package com.tcwong.pengms.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Description 获取Bean类
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
@Component
public class ApplicationBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationBeanUtil.applicationContext = applicationContext;
    }

    /**
     * Description 获取上下文
     *
     * @param
     * @return
     * @author tcwong
     * @date 2020/8/24
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Description 获取bean
     *
     * @param clazz 获取bean 的class
     * @return
     * @author tcwong
     * @date 2020/8/24
     */
    public static  <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
