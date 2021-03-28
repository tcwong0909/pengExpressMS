package com.tcwong.pengms.base;

import com.tcwong.pengms.utils.LocaleMessageUtil;

/**
 * Description 统一response
 *
 * @author tcwong
 * @date 2020/9/1 Since 1.8
 */
public class WebResponse<T> {

    private String code;

    private String message;

    private T data;

    public WebResponse() {}

    public WebResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected WebResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success(T data) {
        return new WebResponse<T>("0000", LocaleMessageUtil.getMessage("0000"), data);
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> success(T data, String message) {
        return new WebResponse<T>("0000", message, data);
    }

    /**
     * Description 异常信息
     *
     * @param errorCode 错误码
     * @return
     * @author tcwong
     * @date 2020/9/1
     */
    public static <T> WebResponse<T> failed(String errorCode) {
        return new WebResponse<T>(errorCode, LocaleMessageUtil.getMessage(errorCode), null);
    }

    /**
     * Description
     *
     * @param errorCode 错误码
     * @param data 异常信息
     * @return
     * @author tcwong
     * @date 2020/9/1
     */
    public static <T> WebResponse<T> failed(String errorCode, T data) {
        return new WebResponse<T>(errorCode, LocaleMessageUtil.getMessage(errorCode), data);
    }

    /**
     * 失败返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> WebResponse<T> failed() {
        return failed("9999");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
