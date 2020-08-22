package com.tcwong.pengms.constant;

/**
 * Description 登陆状态
 *
 * @author tcwong
 * @date 2020/8/22
 * Since 1.8
 */
public enum LoginResultEnum {
    SUCCESS("200","登陆成功"),
    FAILED("400","登陆失败"),
    NOT_EXIST_ACCOUNT("","账号不存在"),
    ERROR_PASSWORD("","密码错误");

    LoginResultEnum(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    private String resultCode;
    private String resultMessage;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
