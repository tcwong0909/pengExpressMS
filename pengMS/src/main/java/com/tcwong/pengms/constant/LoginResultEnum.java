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
    FAILED("430","登陆失败"),
    NOT_EXIST_ACCOUNT("431","账号不存在"),
    ERROR_PASSWORD("432","密码错误"),
    GONE_EXPIRE("433","登陆已过期,请重新登陆"),
    REQUEST_FREQUENT("434","访问频繁，小鹏已经累了，请稍后重试，或联系Tc解锁 0.0");

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
