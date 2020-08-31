package com.tcwong.pengms.base;

/**
 * Description 自定义异常
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public class BaseException extends RuntimeException{

    private String exceptionCode;

    private String exceptionMessage;

    public BaseException(String exceptionCode, String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
