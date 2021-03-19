package com.tcwong.pengms.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Description 系统状态码国际化
 *
 * @author tcwong
 * @date 2020/9/1 Since 1.8
 */
public class LocaleMessageUtil {

    private static MessageSource messageSource;

    public static String getMessage(String codeString) {
        String message;
        if (messageSource == null) {
            messageSource = ApplicationBeanUtil.getBean(MessageSource.class);
        }
        try {
            message = messageSource.getMessage(codeString, null, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            message = messageSource.getMessage("8888", null, LocaleContextHolder.getLocale());
        }
        return message;
    }
}
