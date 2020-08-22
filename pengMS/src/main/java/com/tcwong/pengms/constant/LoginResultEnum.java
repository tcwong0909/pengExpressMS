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
    FAILED("410","登陆失败"),
    NOT_EXIST_ACCOUNT("411","账号不存在"),
    ERROR_PASSWORD("412","密码错误"),
    GONE_EXPIRE("413","登陆已过期,请重新登陆");

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
