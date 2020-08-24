package com.tcwong.pengms.base;

import com.tcwong.pengms.constant.LogOperationType;

import java.lang.annotation.*;

/**
 * Description 日志切面注解
 *
 * @author tcwong
 * @date 2020/8/24
 * Since 1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogFilter {
     //操作类型
     LogOperationType logOperationType();

     //操作详情信息
     String description() default "";
}
