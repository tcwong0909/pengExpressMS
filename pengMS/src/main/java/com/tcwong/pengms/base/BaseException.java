package com.tcwong.pengms.base;

import com.tcwong.pengms.utils.LocaleMessageUtil;

/**
 * Description 自定义异常
 *
 * @author tcwong
 * @date 2020/8/22 Since 1.8
 */
public class BaseException extends RuntimeException {

    private String exceptionCode;

    private Object[] args;

    public BaseException(String exceptionCode) {
        super(LocaleMessageUtil.getMessage(exceptionCode));
        this.exceptionCode = exceptionCode;
        this.args = null;
    }

    public BaseException(String exceptionCode, Object[] args) {
        super(LocaleMessageUtil.getMessage(exceptionCode));
        this.exceptionCode = exceptionCode;
        this.args = args;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
